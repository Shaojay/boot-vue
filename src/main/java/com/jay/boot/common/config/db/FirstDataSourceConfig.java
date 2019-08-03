package com.jay.boot.common.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * 配置主数据源
 * @Configuration 表明这是一个配置类
 * @Primary 默认
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/30 22:23
 */
@Configuration
@MapperScan(basePackages = {"com.jay.boot.mysql.*.mapper"}, sqlSessionFactoryRef = "firstSqlSessionFactory")
public class FirstDataSourceConfig {
    
    /**
     * 默认数据源
     */
    @ConfigurationProperties(prefix = "spring.datasource.first")
    @Bean("firstDataSource")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Primary
    @Bean("firstSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("firstDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 设置mapper.xml 位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/mysql/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    @Primary
    @Bean("firstSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("firstSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
