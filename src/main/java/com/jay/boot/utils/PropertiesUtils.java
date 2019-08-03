package com.jay.boot.utils;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

/**
 * 读取配置文件工具类，暂不支持国际化
 *
 * @author shao
 * @since 1.0
 * Date 2019/4/1 17:14
 */
public class PropertiesUtils {
    
    protected static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
    
    /**
     * 获取JVM配置参数值，没有就去配置文件读取
     *
     * @param key
     * @return
     */
    public static String getValue(String localFile, String key) {
        // 获取JVM参数
        String value = System.getProperty(key);
        if (StringUtils.isBlank(value)) {
            value = getLocalValue(localFile, key);
        }
        return value;
    }
    
    /**
     * 从配置文件读取key对应的值
     *
     * @param localFile 配置文件名
     * @param key       键
     * @return
     */
    public static String getLocalValue(String localFile, String key) {
        String value = null;
        try {
            ResourceBundle resource = ResourceBundle.getBundle(localFile);
            value = resource.getString(key);
        } catch (RuntimeException e) {
            logger.debug("没有对应的配置文件:{}.properties或者key:{}", localFile, key);
        }
        return value;
    }
    
    /**
     * 从JVM配置文件名中取key对应的值,JVM配置名空的话去默认文件中取
     *
     * @param configName JVM 配置文件名参数
     * @param key        键
     * @return           对应值
     */
    public static String getValue(String configName, String key, String localFile) {
        String fileName = System.getProperty(configName);
        String value = getLocalValue(fileName, key);
        if (StringUtils.isBlank(value)) {
            value = getLocalValue(localFile, key);
        }
        return value;
    }
    
}
