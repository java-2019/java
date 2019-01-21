package com.myboot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by lxy on 18-7-26.
 */
@Configuration
public class JdbcDataSourceConfig {

    @Bean(name = "datasource1")
    @Qualifier("datasource1")
    @ConfigurationProperties(prefix = "datasource1.datasource")
    public DataSource spaDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("datasource1") DataSource dataSource){
        return new JdbcTemplate(dataSource);

    }
}
