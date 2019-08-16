package com.jay.boot.job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.Serializable;
import java.util.Date;

/**
 * ClassName SayHelloJob
 *
 * @author 搬砖小能手
 * @since 1.0
 * Date 2019/8/7 23:10
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class SayHelloJob extends QuartzJobBean implements Serializable {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println(new Date()+"===================test add task===================");
    }
}