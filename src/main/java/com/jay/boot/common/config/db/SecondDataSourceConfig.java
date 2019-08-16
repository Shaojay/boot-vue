
package com.jay.boot.common.config.db;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


/**
 * 另一数据源
 *
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/30 23:08
 */

@Configuration
@MapperScan(basePackages = {"com.jay.boot.oracle.*.mapper"}, sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {
    @ConfigurationProperties(prefix = "spring.datasource.second")
    @Bean("secondDataSource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    
    @Bean("secondSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        // 设置mapper.xml 位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/oracle/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
    
    @Bean("secondSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory());
    }
}

