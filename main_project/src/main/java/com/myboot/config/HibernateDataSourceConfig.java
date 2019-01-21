package com.myboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by majf
 * 2018/8/21 18:32
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "girlEntityManagerFactory", transactionManagerRef = "girlTransactionManager", basePackages = "com.myboot.repository")
public class HibernateDataSourceConfig {

    @Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "girlDataSource")
    @Qualifier("girlDataSource")
    @ConfigurationProperties(prefix = "datasource3.datasource")
    public DataSource girlDataSource(){
        return DataSourceBuilder.create().build();
    }

//    @Bean(name = "girlEntityManager")
//    public EntityManager girlEntityManager(@Qualifier("girlDataSource") DataSource dataSource, EntityManagerFactoryBuilder builder){
//        return girlEntityManagerFactory(dataSource, builder).getObject().createEntityManager();
//    }

    @Bean(name = "girlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean girlEntityManagerFactory (@Qualifier("girlDataSource") DataSource dataSource,EntityManagerFactoryBuilder builder){
        return builder
                .dataSource(dataSource)
                .properties(getVendorProperties())
                .packages("com.myboot.entity")
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    private Map<String, Object> getVendorProperties() {
        return jpaProperties.getHibernateProperties(new HibernateSettings());
    }

    @Bean(name = "girlTransactionManager")
    public PlatformTransactionManager girlTransactionManager(@Qualifier("girlDataSource")DataSource dataSource, EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(girlEntityManagerFactory(dataSource, builder).getObject());
    }

}
