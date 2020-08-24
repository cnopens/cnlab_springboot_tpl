package com.ds.dss.componet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ResourceScheduleTask {
    Logger log;

    public ResourceScheduleTask() {
        this.log = LoggerFactory.getLogger((Class) ResourceScheduleTask.class);
    }

    @Scheduled(cron = "* 30 6 * * ?")
    @ConditionalOnProperty(prefix = "scheduling", name = "enabled", havingValue = "true")
    public void scheduledTask() {

    }
}
