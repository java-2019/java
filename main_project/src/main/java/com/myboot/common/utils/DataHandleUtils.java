package com.myboot.common.utils;

import com.myboot.common.constant.Constants;
import com.myboot.mapper.BaseMapper;
import com.myboot.model.BaseModal;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by majf
 * 2018/8/13 18:11
 */
@Slf4j
public class DataHandleUtils {
    /**
     * 根据会所Id和查询的时间段查询数据时，处理无数据的日期也需要返回一条记录
     * @param clazz 实例类class
     * @param list 数据库查询的原始结果
     * @param clubId 查询的会所Id
     * @param startDate 查询的时间段
     * @param endDate 查询的时间段
     * @param <T> 实例类泛型
     * @return newList 原始查询结果list增加了无数据的日期后的newList
     */
    public static  <T extends BaseModal> List<T> addNullDate(Class<?> clazz, List<T> list, String clubId, String startDate, String endDate) {
        Date start = CalendarUtils.parseDate(startDate);
        Date end = CalendarUtils.parseDate(endDate);
        List<T> newList = new ArrayList<T>();
        try {
            while (end.compareTo(start) >= 0) {
                int status = 0;
                for (T stat : list) {
                    if (end.compareTo(stat.getStatDate()) == 0) {//该日期有记录
                        status = 1;
                        newList.add(stat);
                        break;
                    }
                }
                if (status == 0) {//该日期无记录，补一条日期记录
                    T statBeforeCerrentDate = (T) clazz.newInstance();
                    statBeforeCerrentDate.setClubId(clubId);
                    statBeforeCerrentDate.setStatDate(end);
                    newList.add(statBeforeCerrentDate);
                }
                end = CalendarUtils.dateAddOrSub(end, Calendar.DATE, -1);
            }
        } catch (Exception e) {
            log.error("处理无数据的日期 error:", e);
        }
        return newList;
    }


    /**
     * 分批保存：防止一次性保存的数据量过大，按记录条数分批保存
     * @param statList 需要保存的数据
     * @param mapper 保存数据使用的mapper对应的bean实例
     * @param <T> 实例类泛型
     * @param <M> mapper泛型
     */
    public static  <T, M extends BaseMapper> void batchSave(List<T> statList , M mapper) {
        //分批保存
        List<T> newList = new ArrayList<T>();
        try {
            for(int i = 0; i < statList.size(); i++){
                newList.add(statList.get(i));
                if((Constants.DEFAULT_SAVE_DATALIMIT == newList.size()||i == statList.size()-1) && newList.size()  > 0){
                    mapper.insertList(newList);
                    newList.clear();
                }
            }
        } catch (Exception e) {
            log.error("分批保存 error:", e);
        }
    }
}
