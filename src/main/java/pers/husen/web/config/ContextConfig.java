package pers.husen.web.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ImportResource("classpath:/config/application-context.xml")
public class ContextConfig {

    @Value("${jdbc.driver}")
    private String driver = "com.mysql.cj.jdbc.Driver";
    @Value("${jdbc.url}")
    private String url = "jdbc:mysql://127.0.0.1:3306/web?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
    @Value("${jdbc.username}")
    private String username= "root";
    @Value("${jdbc.password}")
    private String password = "123456";

    /**
     * 生效,但是对这个类的成员变量无效
     * @return
     */
    /*@Bean
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
        cfg.setLocation(new ClassPathResource("config/jdbc.properties"));
        return cfg;
    }*/

    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 配置文件位置
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("config/mybatis-config.xml"));
        // 配置映射器类XML文件位置
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*Mapper.xml"));
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
        return sqlSessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    /*@Bean
    public EmployeeDao mapperFactoryBean(SqlSessionFactory sqlSessionFactory) throws Exception {
        MapperFactoryBean mapperFactoryBean = new MapperFactoryBean();
        mapperFactoryBean.setMapperInterface(EmployeeDao.class);
        mapperFactoryBean.setSqlSessionFactory(sqlSessionFactory);
        return (EmployeeDao) mapperFactoryBean.getObject();
    }*/

    /**
     * MapperScannerConfigurer 属性不支持使用了 PropertyPlaceholderConfigurer 的属性替换,
     * 因为会在 Spring 启动之前来它加载。
     * 但是,你可以使用 PropertiesFactoryBean 和 SpEL 表达式来作为替代。
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
        scannerConfigurer.setBasePackage("pers.husen.web.dao");
        scannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
        return scannerConfigurer;
    }

    @Bean
    public ViewResolver viewResolver() {
        return new InternalResourceViewResolver();
    }
}
