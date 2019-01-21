package com.myboot.service.stat;

import com.myboot.model.ClubPaymentStat;

import java.util.List;

/**
 * Created by majf
 * 2018/7/30 14:24
 */

public interface ClubPaymentStatService {

    List<ClubPaymentStat> selectByClubIdAndDate(String clubId, String startDate, String endDate);
}
