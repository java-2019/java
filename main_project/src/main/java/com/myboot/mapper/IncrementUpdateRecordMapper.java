package com.myboot.mapper;

import com.myboot.model.IncrementUpdateRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;

@Mapper
@Component
public interface IncrementUpdateRecordMapper {

    int insert(IncrementUpdateRecord record);

    int insertSelective(IncrementUpdateRecord record);

    int updateByPrimaryKeySelective(IncrementUpdateRecord record);

    int updateByPrimaryKey(IncrementUpdateRecord record);

    IncrementUpdateRecord findByType(@Param("type") String type);

    int updateLastRecord(@Param("id") Long id, @Param("lastRecordId") String lastRecordId,
                         @Param("modifyTime") Date date, @Param("preLastRecordId") String preLastRecordId);
}