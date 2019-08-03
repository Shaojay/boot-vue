/*
package com.jay.boot.learn.quartz;

import com.jay.boot.job.ChickenJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

*/
/**
 * ClassName MySchedular
 *
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/29 19:36
 *//*

public class MySchedular {
    
    */
/**
     * , 表示或
     * - 表示区间
     * * 表示每
     *
     * 秒            0-59           , - / *
     * 分            0-59           , - / *
     * 时            0-23           , - / *
     * 日            1-31           , - / * ? L W C
     * 月        1-12或者JAN-DEC     , - / *
     * 周        1-7 或者SUN-SAT     , - / * ? L C #
     * 年（非必填）empty, 1970-2099   , - / *
     * @param args
     * @throws SchedulerException
     *//*

    public static void main(String[] args) throws SchedulerException {
        // 创建一个Job，与Job类相绑定
        JobDetail jobDetail = JobBuilder.newJob(ChickenJob.class).withIdentity("firstJob", "firstGroup")
                .withDescription("My First Job")
                .usingJobData("begin", "first").build();
    
        // 创建一个trigger实例，当前为SimpleTrigger
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withDescription("My Trigger")
                .withIdentity("trigger1", "group2")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(2)
                .repeatForever())
                .build();
        
        // 创建Scheduler工厂并创建Scheduler 调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        
        // 开启调度
        scheduler.start();
        // 关联任务和触发器，执行任务作业
        scheduler.scheduleJob(jobDetail, trigger);
        
    
        // 创建CronTrigger
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
                .build();
    }
}
*/
