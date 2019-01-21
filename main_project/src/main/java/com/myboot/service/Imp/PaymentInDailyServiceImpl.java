package com.myboot.service.Imp;

import com.myboot.entity.PaymentInDaily;
import com.myboot.repository.PaymentInDailyDao;
import com.myboot.service.PaymentInDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by majf
 * 2018/7/26 10:30
 */

@Service("paymentInDailyService")
@Transactional
public class PaymentInDailyServiceImpl implements PaymentInDailyService {

    @Autowired
    PaymentInDailyDao paymentInDailyDao;


    @Override
    public List<PaymentInDaily> getPaymentInDaily(String clubId, String payTimeStart, String payTimeEnd) {
        List<PaymentInDaily> list = paymentInDailyDao.getPaymentInDaily(clubId,payTimeStart,payTimeEnd);
        return list;

    }

    @Override
    public List<PaymentInDaily> getPaymentInDailyXml(String clubId, String payTimeStart, String payTimeEnd) {
        List<PaymentInDaily> list = paymentInDailyDao.getPaymentInDailyXml(clubId,payTimeStart,payTimeEnd);
        return list;
    }


}
