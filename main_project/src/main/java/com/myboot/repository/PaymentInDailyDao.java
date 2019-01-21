package com.myboot.repository;

import com.myboot.entity.PaymentInDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by majf
 * 2018/7/25 20:15
 */

public interface PaymentInDailyDao extends JpaRepository<PaymentInDaily, Long> {
    List<PaymentInDaily> getPaymentInDaily(@Param(value = "clubId") String clubId,
                                           @Param(value = "payTimeStart") String payTimeStart,
                                           @Param(value = "payTimeEnd") String payTimeEnd);

    List<PaymentInDaily> getPaymentInDailyXml(@Param(value = "clubId") String clubId,
                                              @Param(value = "payTimeStart") String payTimeStart,
                                              @Param(value = "payTimeEnd") String payTimeEnd);
}
