package com.myboot.controller;

import com.myboot.common.constant.MediaTypes;
import com.myboot.model.ClubPaymentStat;
import com.myboot.service.stat.ClubPaymentStatService;
import com.myboot.service.stat.IncrementUpdateStatTempalte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by majf
 * 2018/7/30 14:12
 */
@Slf4j
@Controller
@RequestMapping(value = "/api/payment")
public class PaymentController {

    @Autowired
    ClubPaymentStatService clubPaymentStatService;

    @Autowired
    @Qualifier("clubPaymentStatService")
    IncrementUpdateStatTempalte incrementUpdateStatTempalte;

    //test
    @RequestMapping(value = "/daily/stat", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public void doPaymentInDaily(){
        incrementUpdateStatTempalte.incrementUpdateStatistic();
    }

    /**
     * 查询已统计的数据
     * @return
     */
    @RequestMapping(value = "/daily", produces = MediaTypes.JSON_UTF_8)
    @ResponseBody
    public List<ClubPaymentStat> selectByClubIdAndDate(String clubId, String startDate, String endDate){
        log.info("Payment ： 查询已统计的数据");
        List<ClubPaymentStat> list = clubPaymentStatService.selectByClubIdAndDate(clubId, startDate, endDate);
        return list;
    }


}
