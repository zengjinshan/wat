package com.fh.framewrok.task;

import com.fh.service.demo.demo.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2016/12/21.
 */
@Component
public class DemoTask {

    private static Logger logger= LoggerFactory.getLogger(DemoTask.class);

    @Autowired
    private DemoService demoService;

    /**
     * 每月一号凌晨一点执行
     */
    @Scheduled(cron = "0 0 1 1 * ?")
    public void start(){
            logger.info("============清除冗余token开始=============");
            try {
                demoService.insertDemo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            logger.info("============清除冗余token结束=============");
    }
}
