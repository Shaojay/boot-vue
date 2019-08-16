package com.jay.boot.common.config.quartz;

import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * ClassName SpringJobFactory
 *
 * job是在quartz中实例化出来的，不受spring的管理。所以就导致job类里注入的service为null,
 * 注入不进去，SpringBoot 2.x不存在这个问题，内部解决了
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/29 0:27
 */

@Component
public class SpringJobFactory extends AdaptableJobFactory {

   /* @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
        // 调用父类的方法
        Object jobInstance = super.createJobInstance(bundle);
        // 进行注入
        capableBeanFactory.autowireBean(jobInstance);
        return jobInstance;
    }*/
}
