package com.jay.boot.common.config.quartz;

import com.jay.boot.job.SayHelloJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName QuartzUtil
 *
 * 这个关于暂停、重启等一些方法没写,很简单就是封装一下scheduler的方法
 *
 * @author 搬砖小能手
 * @since 1.0
 * Date 2019/8/7 23:05
 */
@Slf4j
@Component
public class QuartzUtil {
    
    /**
     * 获取JobDetail
     * @param taskInfo
     * @return
     */
    public static JobDetail getJobDetail(TaskInfo taskInfo) {
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        // durability 表示任务完成之后是否依然保留到数据库，默认false
        jobDetailFactoryBean.setDurability(true);
        //当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务
        jobDetailFactoryBean.setRequestsRecovery(true);
        jobDetailFactoryBean.setJobClass(SayHelloJob.class);
        jobDetailFactoryBean.setDescription("fly");
        jobDetailFactoryBean.setName("sayHello-2");
        Map<String,String> jobDataAsMap = new HashMap<>();
        jobDataAsMap.put("targetObject","hello");
        //spring 中bean的名字
        jobDataAsMap.put("targetMethod","auto");
        //执行方法名
        jobDetailFactoryBean.setJobDataAsMap(jobDataAsMap);
        jobDetailFactoryBean.afterPropertiesSet();
        return jobDetailFactoryBean.getObject();
    }
    
    /**
     * 获取CronTrigger
     * @param taskInfo
     * @return
     */
    public static CronTrigger getTrigger(TaskInfo taskInfo) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        //log.error("jobDetail -> "+jobDetail);
        // cronTriggerFactoryBean.setJobDetail(jobDetail);
        //秒 分 小时 日 月 星期 年  每10分钟
        cronTriggerFactoryBean.setCronExpression("0/15 * * * * ? *");
        cronTriggerFactoryBean.setName("printTimeCronTrigger-2");
        //cronTriggerFactoryBean.setJobDetail(getJobDetail(null));
        //trigger超时处理策略 默认1：总是会执行头一次 2:不处理
        cronTriggerFactoryBean.setMisfireInstruction(2);
        try {
            cronTriggerFactoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cronTriggerFactoryBean.getObject();
    }
    
    /**
     * 获取TriggerKey， 默认使用jobName和jobGroup 生成
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static TriggerKey getTriggerKey(String jobName, String jobGroup) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        return triggerKey;
    }
    
    public static TriggerKey getTriggerKey(TaskInfo taskInfo) {
        return getTriggerKey(taskInfo.getJobName(), taskInfo.getJobGroup());
    }
    
    /**
     * 获取JobKey
     * @param jobName
     * @param jobGroup
     * @return
     */
    public static JobKey getJobKey(String jobName, String jobGroup) {
        return JobKey.jobKey(jobName, jobGroup);
    }
    
    /**
     * 获取JobKey
     * @param taskInfo
     * @return
     */
    public static JobKey getJobKey(TaskInfo taskInfo) {
        return getJobKey(taskInfo.getJobName(), taskInfo.getJobGroup());
    }
    
    public static void scheduler(Scheduler scheduler, TaskInfo taskInfo) {
        JobDetail jobDetail = getJobDetail(taskInfo);
        CronTrigger trigger = getTrigger(taskInfo);
        try {
            // scheduleJob方法 会保存 jobdetail 和trigger到表中，如果表中已存在
            // 其中之一，则会报错，所以判断下，已有的话就重启任务，暂不知其他解决办法
            if (!scheduler.checkExists(jobDetail.getKey()) || !scheduler.checkExists(trigger.getKey())) {
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                scheduler.resumeJob(trigger.getJobKey());
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    
}
