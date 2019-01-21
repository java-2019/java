package com.myboot.service.stat.impl;

import com.myboot.common.JdbcDaoImpl;
import com.myboot.common.constant.Constants;
import com.myboot.common.utils.CalendarUtils;
import com.myboot.common.utils.DataHandleUtils;
import com.myboot.mapper.ClubPaymentStatMapper;
import com.myboot.model.ClubPaymentStat;
import com.myboot.service.stat.ClubPaymentStatService;
import com.myboot.service.stat.IncrementUpdateStatTempalte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;


/**
 * Created by majf
 * 2018/7/30 14:29
 */
@Service("clubPaymentStatService")
@Transactional
@Slf4j
public class ClubPaymentStatServiceImpl extends IncrementUpdateStatTempalte implements ClubPaymentStatService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JdbcDaoImpl jdbcDao;

    @Autowired
    private ClubPaymentStatMapper clubPaymentStatMapper;


    /**
     * 查询已统计的数据
     * @return
     */
    @Override
    public List<ClubPaymentStat> selectByClubIdAndDate(String clubId, String startDate, String endDate){
        List<ClubPaymentStat> list = clubPaymentStatMapper.selectByClubIdAndDate(clubId, startDate, endDate);
        //处理无数据的日期
        return  DataHandleUtils.addNullDate(ClubPaymentStat.class, list, clubId, startDate, endDate);
    }

    /**
     * 具体日期统计数据（数据查询太慢了，一次性查出8天数据再用java语言统计）
     * @param Start
     * @return
     */
    public List<ClubPaymentStat> getDaily(Date Start) {
        log.info("Payment ： 统计数据");
        Date tomorrow = CalendarUtils.dateAddOrSub(Start, Calendar.DATE, 1);
        Date sevenDayAgo = CalendarUtils.dateAddOrSub(Start, Calendar.DATE, -7);
        List<Object> params = new ArrayList<Object>();
        String sql = "SELECT club_id AS clubId, DATE_FORMAT(pay_time, '%Y-%m-%d') AS statDate,  NOW() AS modifyTime,COUNT(1) AS count, SUM(amount) AS amount, COUNT(DISTINCT user_id) AS persons\n" +
                "     FROM\n" +
                "      spa_payment\n" +
                "     WHERE STATUS = 1\n" +
                "     AND business_type NOT IN ('withdrawal', 'user_reward')\n" +
                "     AND spa_payment.pay_time >= ? \n" +
                "     AND spa_payment.pay_time < ?\n" +
                "     GROUP BY statDate, clubId  " +
                "ORDER by statDate desc"  ;
        params.add(sevenDayAgo);
        params.add(tomorrow);
        List<ClubPaymentStat> list = jdbcTemplate.query(sql, params.toArray(), new BeanPropertyRowMapper<>(ClubPaymentStat.class));
                //jdbcDao.querySpaDataList(sql, params.toArray(), ClubPaymentStat.class);
        return list;
    }


    /**
     * 按时间段每天统计
     * @param startDate
     * @param endDate
     * @return
     */
    public List<ClubPaymentStat> getData(String startDate, String endDate){
        Date start = CalendarUtils.parseDate(startDate);
        Date end = CalendarUtils.parseDate(endDate);
        //初始化保存到数据库的list
        List<ClubPaymentStat> list = new ArrayList<ClubPaymentStat>();
        //按时间段每天都要统计
        while (start.compareTo(end) < 0) {
            List<ClubPaymentStat> listDaily = getDaily(start);
            list.addAll(mergeList(listDaily, start));
            start = CalendarUtils.dateAddOrSub(start, Calendar.DATE, 1);
        }
        return list;
    }

    /**
     * 处理当天统计和前7天统计
     */
    public List<ClubPaymentStat> mergeList(List<ClubPaymentStat> lists, Date start ){
        List<ClubPaymentStat> newList = new ArrayList<ClubPaymentStat>();
        Iterator<ClubPaymentStat> iterator = lists.iterator();
        while (iterator.hasNext()){//增加当日统计数据
            ClubPaymentStat stat= iterator.next();
            if(stat.getStatDate().compareTo(start) == 0){
                newList.add(stat);
                iterator.remove();
            }else {
                break;
            }
        }

        for(ClubPaymentStat stat2 : newList){
            Iterator<ClubPaymentStat> iterator2 = lists.iterator();
            while (iterator2.hasNext()){//增加前7天统计数据
                ClubPaymentStat stat= iterator2.next();
                if(stat2.getClubId().equals(stat.getClubId())){
                    mergeProperty(stat2,stat, null);
                    iterator2.remove();
                }
            }
        }
        List<ClubPaymentStat> newList1 = new ArrayList<ClubPaymentStat>();
        for(ClubPaymentStat stat3 : lists) {
            Iterator<ClubPaymentStat> iterator3 = newList1.iterator();
            int status = 0;
            while (iterator3.hasNext()) {//增加当日无数据但前7天统计有数据
                ClubPaymentStat stat = iterator3.next();
                if (stat3.getClubId().equals(stat.getClubId())) {//clubId已增加过，直接累计前7天汇总
                    mergeProperty(stat,stat3, null);
                    status = 1;
                }
            }
            if(status == 0){//clubId没增加过，直接累计前7天汇总
                mergeProperty(stat3,null, start);
                newList1.add(stat3);
            }
        }
        newList.addAll(newList1);
        return newList;
    }

    /**
     * 合并属性值，以stat1记录为主记录
     */
    public ClubPaymentStat mergeProperty(ClubPaymentStat stat1, ClubPaymentStat stat2, Date start) {
        if(start == null){
            stat1.setTotalCount(stat1.getTotalCount()+ stat2.getCount());
            stat1.setTotalAmount(stat1.getTotalAmount()+ stat2.getAmount());
            stat1.setTotalPersons(stat1.getTotalPersons()+ stat2.getPersons());
        }else {
            stat1.setTotalCount(stat1.getCount());
            stat1.setTotalAmount(stat1.getAmount());
            stat1.setTotalPersons(stat1.getPersons());
            stat1.setCount(0);
            stat1.setAmount(0);
            stat1.setPersons(0);
            stat1.setStatDate(start);
        }

        return stat1;
    }


    @Override
    protected String doUpdateStatistic(String lastId) {
        log.info("Payment ：【doUpdateStatistic】");
        String startDate = Constants.DEFAULT_START_DATE;
        if(!StringUtils.isEmpty(lastId)){
            startDate = lastId;
        }
        String endDate =  CalendarUtils.format(CalendarUtils.dayBegin( new Date())).substring(0,10);

        //统计数据
        List<ClubPaymentStat> list = getData(startDate, endDate);
        //保存统计数据
        if (list.size()>0){
            DataHandleUtils.batchSave(list, clubPaymentStatMapper);
        }
        return endDate;
    }

    @Override
    protected String getStatType() {
        return Constants.STAT_TYPE_PAYMENT;
    }

    @Override
    protected String getStatisticalDescription() {
        return "支付统计最后日期";
    }
}
