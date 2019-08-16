package com.jay.boot.common.config.quartz;

/**
 * ClassName SchedulerConfig
 *
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/31 22:55
 */

import com.jay.boot.job.ChickenJob;
import com.jay.boot.job.FlyJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class SchedulerConfig implements SchedulerFactoryBeanCustomizer, ApplicationRunner{
    
    /**
     * 1. 在这定义一个JobDetailFactoryBean 和CronTriggerFactoryBean，
     * 启动后自动执行任务
     * 2. 调用对应QuartzUtil的方法可以实现动态添加、删除等等
     * @return
     */
    @Bean
    public JobDetailFactoryBean printTimeJobDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        // durability 表示任务完成之后是否依然保留到数据库，默认falses
        jobDetailFactoryBean.setDurability(true);
        //当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务
        jobDetailFactoryBean.setRequestsRecovery(true);
        jobDetailFactoryBean.setJobClass(ChickenJob.class);
        jobDetailFactoryBean.setDescription("打印时间定时器2");
        //jobDetailFactoryBean.setGroup();
        Map<String,String> jobDataAsMap = new HashMap<>();
        jobDataAsMap.put("targetObject","printTimeQuartz");
        //spring 中bean的名字
        jobDataAsMap.put("targetMethod","execute");
        //执行方法名
        jobDetailFactoryBean.setJobDataAsMap(jobDataAsMap);
        return  jobDetailFactoryBean;
    }
    
    /**
     * 定义一个Trigger
     * @return
     */
    @Bean
    public CronTriggerFactoryBean printTimeCronTrigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        // 设置jobDetail
        System.out.println(printTimeJobDetail().getObject());
        cronTriggerFactoryBean.setJobDetail(printTimeJobDetail().getObject());
        //秒 分 小时 日 月 星期 年  每10分钟
        cronTriggerFactoryBean.setCronExpression("0/5 * * * * ? *");
        //trigger超时处理策略 默认1：总是会执行头一次 2:不处理
        cronTriggerFactoryBean.setMisfireInstruction(2);
        return cronTriggerFactoryBean;
    }
    
    @Bean
    public JobDetailFactoryBean flyJobDetail(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        // durability 表示任务完成之后是否依然保留到数据库，默认false
        jobDetailFactoryBean.setDurability(true);
        //当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务
        jobDetailFactoryBean.setRequestsRecovery(true);
        jobDetailFactoryBean.setJobClass(FlyJob.class);
        jobDetailFactoryBean.setDescription("fly");
        Map<String,String> jobDataAsMap = new HashMap<>();
        jobDataAsMap.put("targetObject","fly_job");
        //spring 中bean的名字
        jobDataAsMap.put("targetMethod","execute");
        //执行方法名
        jobDetailFactoryBean.setJobDataAsMap(jobDataAsMap);
        return  jobDetailFactoryBean;
    }
    
    /**
     * * , 表示或
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
     * 定义一个Trigger
     * @return
     */
    @Bean
    public CronTriggerFactoryBean flyCronTrigger() {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        System.out.println(flyJobDetail().getObject());
        cronTriggerFactoryBean.setJobDetail(flyJobDetail().getObject());
        //秒 分 小时 日 月 星期 年  每10分钟
        cronTriggerFactoryBean.setCronExpression("0/10 * * * * ? *");
        //trigger超时处理策略 默认1：总是会执行头一次 2:不处理
        cronTriggerFactoryBean.setMisfireInstruction(2);
        return cronTriggerFactoryBean;
    }
/**
     * 初始化Quartz监听器,让Spring boot启动时初始化Quartz
     *
     */

    /*@Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }*/
    
    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
        schedulerFactoryBean.setConfigLocation(new ClassPathResource("/application-quartz.yml"));
    }
    
    @Autowired
    private Scheduler scheduler;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobDetail jobDetail = QuartzUtil.getJobDetail(null);
        CronTrigger trigger = QuartzUtil.getTrigger(null);
        /*if (!scheduler.checkExists(jobDetail.getKey())){
            scheduler.scheduleJob(jobDetail, trigger);
        } else {
            scheduler.resumeJob(jobDetail.getKey());
        }*/
        
        scheduler.scheduleJob(jobDetail, trigger);
    }
    
}
