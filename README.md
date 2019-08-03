# boot-vue
###学习
####1. 配置拦截器SessionInterceptor，并在CustWebConfigurer中配添加拦截器,
   其中CustWebConfigurer实现WebMvcConfigurer接口，实现此接口可重写单个方法以完成定制功能
```java
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
    /**
     * 根据handler 可以获取处理器的相关内容，包括注解，bean，参数等等
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }
}
@Configuration
public class CustWebConfigurer implements WebMvcConfigurer {
    
    @Autowired
    private SessionInterceptor sessionInterceptor;
    
    @Autowired
    private CustFilter custFilter;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截路径和放行的路径
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**")
            .excludePathPatterns("/login.do", "/", "/user/login");
    }
 }
```
####2. 配置Filter

####3. 配置多数据源
#####3.1 先把spring boot自带的DataSourceAutoConfiguration 禁用掉，因为它会读取application.properties文件的spring.datasource.* 属性并自动配置单个的数据源
```java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
```
#####3.2 在application.properties配置文件中添加数据源的连接信息
这个地方因为spring boot2.0以上版本适配的Mysql版本较高，驱动类为com.mysql.cj.jdbc.Driver，url也需要加上时区，不然会乱码导致连接报错
```properties
## 1. 数据源1
spring.datasource.first.url=jdbc:mysql://localhost:3306/wxorder?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false
spring.datasource.first.username=root
spring.datasource.first.password=root
spring.datasource.first.driver-class-name=com.mysql.cj.jdbc.Driver
## 2. 数据源2
spring.datasource.second.url=jdbc:mysql://localhost:3306/quartz?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false
spring.datasource.second.username=root
spring.datasource.second.password=root
spring.datasource.second.driver-class-name=com.mysql.cj.jdbc.Driver
```
#####3.3 配置数据源
```java
/**
* basePackages 配置扫描生成动态代理类的Mapper接口的包，两个数据源配置不同路径，则调用不同路径下的方法会使用相应的数据源
* 主数据源
* @Configuration 注解注释的类也会被注册到Spring的Bean容器中
* @ConfigurationProperties 会自动将properties文件中以"spring.datasource.first"开头的属性自动注入标注的实体内
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
    public SqlSessionFactory sqlSessionFactory(@Qualifier("firstDataSourceConfig") DataSource dataSource) throws Exception {
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

/**
* 第二个数据源
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
```
