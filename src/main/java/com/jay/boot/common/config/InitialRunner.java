package com.jay.boot.common.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * ClassName InitialRunner
 *
 * Spring容器启动后做一些初始化操作
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/29 0:13
 */
@Component
public class InitialRunner implements ApplicationRunner{
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println("Spring Context has started");
    }
}
