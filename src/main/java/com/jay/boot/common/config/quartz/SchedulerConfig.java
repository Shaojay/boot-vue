//package com.jay.boot.common.config.quartz;
//
///**
// * ClassName SchedulerConfig
// *
// * @author shao.meng
// * @since 1.0
// * Date 2019/7/31 22:55
// */
//
//import org.quartz.Scheduler;
//import org.quartz.ee.servlet.QuartzInitializerListener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.config.PropertiesFactoryBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.scheduling.quartz.SchedulerFactoryBean;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.util.Properties;
//
//@Configuration
//public class SchedulerConfig {
//
//    @Autowired
//    @Qualifier("firstDataSource")
//    private DataSource dataSource;
//
//    @Bean(name="SchedulerFactory")
//    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
//        SchedulerFactoryBean factory = new SchedulerFactoryBean();
//        //factory.setDataSource(dataSource);
//        factory.setQuartzProperties(quartzProperties());
//        return factory;
//    }
//
//
//    /**
//     * 加载Quartz配置
//     *
//     */
//
//    @Bean
//    public Properties quartzProperties() throws IOException {
//        //使用Spring的PropertiesFactoryBean对属性配置文件进行管理
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        propertiesFactoryBean.afterPropertiesSet();
//
//        Properties properties = propertiesFactoryBean.getObject();
///**
//        // 账号密码解密
//        Crypter crypter = CrypterFactory.getCrypter(CrypterFactory.AES_CBC);
//        String user = properties.getProperty("org.quartz.dataSource.qzDS.user");
//        if (user != null) {
//            user = crypter.decrypt(user);
//            properties.setProperty("org.quartz.dataSource.qzDS.user", user);
//        }
//        String password = properties.getProperty("org.quartz.dataSource.qzDS.password");
//        if (password != null) {
//            password = crypter.decrypt(password);
//            properties.setProperty("org.quartz.dataSource.qzDS.password", password);
//        }
//        */
//
//        return properties;
//    }
//
//
///**
//     * 初始化Quartz监听器,让Spring boot启动时初始化Quartz
//     *
//     */
//
//    @Bean
//    public QuartzInitializerListener executorListener() {
//        return new QuartzInitializerListener();
//    }
//
//
///**
//     * 通过SchedulerFactoryBean获取Scheduler的实例
//     */
//
//    @Bean(name="Scheduler")
//    public Scheduler scheduler() throws IOException {
//        return schedulerFactoryBean().getScheduler();
//    }
//}
