//package com.example.clienttwo.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import io.seata.rm.datasource.DataSourceProxy;
//import io.seata.spring.annotation.GlobalTransactionScanner;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//
///**
// * @author jimin.jm@alibaba-inc.com
// * @date 2019/06/14
// */
//@Configuration
//public class DataSourceConfiguration {
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource dataSource() {
//        DruidDataSource druidDataSource = new DruidDataSource();
//        return druidDataSource;
//    }
//
//    @Primary
//    @Bean("dataSourceProxy")
//    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
//        return new DataSourceProxy(dataSource);
//    }
//
//    @Bean("jdbcTemplate")
//    @ConditionalOnBean(DataSourceProxy.class)
//    public JdbcTemplate jdbcTemplate(DataSourceProxy dataSourceProxy) {
//        return new JdbcTemplate(dataSourceProxy);
//    }
//}
