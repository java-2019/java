package com.myboot.mapper;

import com.myboot.model.ClubPaymentStat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 
 * 会所支付统计 Mapper
 * @author lxy@xiaomodo.com  created at Fri Jul 27 20:41:45 CST 2018
 * 
 */
@Mapper
@Repository("clubPaymentStatMapper")
public interface ClubPaymentStatMapper extends BaseMapper {

    int insert(ClubPaymentStat clubPaymentStat);

    List<ClubPaymentStat> selectByClubIdAndDate(@Param("clubId") String clubId,
                                                @Param("startDate") String startDate,
                                                @Param("endDate") String endDate);



}
