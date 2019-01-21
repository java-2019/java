package com.myboot.web;

import com.myboot.entity.PaymentInDaily;
import com.myboot.service.PaymentInDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by linms@xiaomodo.com on 16-7-23.
 */
@Controller
@RequestMapping(value = "/club/credit")
public class PaymentController{


    @Autowired
    PaymentInDailyService paymentInDailyService;



    @RequestMapping(value = "/payment/in/daily/procedure", method = RequestMethod.GET)
    @ResponseBody
    public List<PaymentInDaily> getPaymentInDailyProcedure(){
        List<PaymentInDaily> list = paymentInDailyService.getPaymentInDaily("952748925440237568","2016-07-26 00:00:00","2018-07-25 00:00:00");
        return list;
    }

    @RequestMapping(value = "/payment/in/daily/xml",  method = RequestMethod.GET)
    @ResponseBody
    public List<PaymentInDaily> getPaymentInDailyXml(){
        List<PaymentInDaily> list = paymentInDailyService.getPaymentInDailyXml("952748925440237568","2016-07-26 00:00:00","2018-07-25 00:00:00");
        return list;
    }

}
