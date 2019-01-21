package com.myboot.quartz;

import com.myboot.service.stat.IncrementUpdateStatTempalte;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by majf
 */
@Component
@Configuration
@EnableScheduling
@Slf4j
public class ClubStatTask {


    @Autowired
    @Qualifier("clubPaymentStatService")
    private IncrementUpdateStatTempalte paymentStatService;



//    @Scheduled(cron = "0 20 12  * * * ")
    public void xmdDataStat(){
        try {
            log.info("club payment data ");
            paymentStatService.incrementUpdateStatistic();
        }catch (Exception e){
            log.error("statistic error " + e.getMessage());
        }
        log.info("club stat end");
    }
}
