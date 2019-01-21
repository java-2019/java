package com.myboot.service.stat;

import com.myboot.common.BusinessException;
import com.myboot.mapper.IncrementUpdateRecordMapper;
import com.myboot.model.IncrementUpdateRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 增量更新统计服务模板
 * Created by majf
 */
@Transactional
public abstract class IncrementUpdateStatTempalte {

    @Autowired
    private IncrementUpdateRecordMapper updateRecordMapper;

    /**
     * 增量更新统计数据
     */
    public void incrementUpdateStatistic(){
        //获取最新更新记录
        String lastId = null;
        String type  = getStatType();
        IncrementUpdateRecord updateRecord = updateRecordMapper.findByType(type);
        if(updateRecord != null){
            lastId = updateRecord.getLastRecordId();
        }

        //统计更新
        String incrementalId = doUpdateStatistic(lastId);

        //将这一次的更新时间刷新
        Date curDate = new Date();
        if (null != updateRecord) {
            if(updateRecordMapper.updateLastRecord(updateRecord.getId(), incrementalId, curDate, updateRecord.getLastRecordId()) <= 0){
                throw new BusinessException("更新" + getStatisticalDescription() + "记录异常,或许其他进程已更新记录");
            }
        } else {
            IncrementUpdateRecord record = new IncrementUpdateRecord();
            record.setType(type);
            record.setDescription(getStatisticalDescription());
            record.setLastRecordId(incrementalId);
            record.setModifyTime(curDate);
            updateRecordMapper.insert(record);
        }
    }

    /**
     * 实现统计更新
     * @param lastId　上一次更新标记值，增量更新标记可以是时间戳,id等
     * @return 本次更新的最新记录标记值
     */
    protected abstract String doUpdateStatistic(String lastId);

    /**
     * 统计业务类型
     * @return
     */
    protected abstract String getStatType();

    /**
     * 统计业务描述信息
     * @return
     */
    protected abstract String getStatisticalDescription();

}

