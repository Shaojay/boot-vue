package com.jay.boot.utils;

/*
import org.apache.ibatis.io.Resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
*/

/**
 * redis 客户端连接
 */
public class RedisClient {

    /*private static JedisCluster jedisCluster = null;

    protected static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    protected static final String fileName = "redis.properties";

    public synchronized static JedisCluster getJedisCluster() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(fileName);
            properties.load(inputStream);
        } catch (IOException e) {
            logger.debug("加载资源文件异常"+e.getLocalizedMessage());
        }
        String ip = properties.getProperty("redis.ip");
        //这里集群master为7001，7002，7003
        int port1 = Integer.parseInt(properties.getProperty("redis.port4"));
        int port2 = Integer.parseInt(properties.getProperty("redis.port2"));
        int port3 = Integer.parseInt(properties.getProperty("redis.port3"));
        System.out.println(port1);
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        *//*config.setMaxTotal(MAX_ACTIVE);
        config.setMaxIdle(MAX_IDLE);
        config.setMaxWaitMillis(MAX_WAIT);
        config.setTestOnBorrow(TEST_ON_BORROW);*//*

        // 集群模式
        Set<HostAndPort> nodes = new HashSet<>();

        HostAndPort hostAndPort1 = new HostAndPort(ip, port1);
        HostAndPort hostAndPort2 = new HostAndPort(ip, port2);
        HostAndPort hostAndPort3 = new HostAndPort(ip, port3);

        nodes.add(hostAndPort1);
        nodes.add(hostAndPort2);
        nodes.add(hostAndPort3);

        
        // 只有当jedisCluster为空时才实例化
        if (jedisCluster == null) {
            //System.out.println("kong  ==============");
            jedisCluster = new JedisCluster(nodes, poolConfig);
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            logger.debug("关闭流异常"+e.getLocalizedMessage());
        }
        return jedisCluster;
    }*/
}

