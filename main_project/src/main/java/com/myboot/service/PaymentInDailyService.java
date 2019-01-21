package com.myboot.service;

import com.myboot.entity.PaymentInDaily;
import java.util.List;

/**
 * Created by majf
 * 2018/7/26 10:17
 */
public interface PaymentInDailyService {
    List<PaymentInDaily> getPaymentInDaily(String clubId, String payTimeStart, String payTimeEnd);

    List<PaymentInDaily> getPaymentInDailyXml(String clubId, String payTimeStart, String payTimeEnd);

}
