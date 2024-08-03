package com.example.multidc.config;

import java.util.HashMap;
import javax.sql.DataSource;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "departmentEntityManagerFactory",
        transactionManagerRef = "departmentTransactionManager",
        basePackages = {"com.example.multidc.repository.department"}
)
public class DepartmentDataSourceConfig {

    @Primary
    @Bean(name = "departmentProperties")
    @ConfigurationProperties("spring.datasource.department")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "departmentDatasource")
    public DataSource dataSource(@Qualifier("departmentProperties") DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "departmentEntityManagerFactory")
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(
            EntityManagerFactoryBuilder builder,
            @Qualifier("departmentDatasource") DataSource dataSource
    ) {
        var jpaProperties = new HashMap<String, Object>();
        jpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");
//        jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProperties.put("hibernate.show_sql", "true");

        return builder.dataSource(dataSource)
                .properties(jpaProperties)
                .packages("com.example.multidc.entity.department")
                .persistenceUnit("department")
                .build();
    }

    @Primary
    @Bean(name = "departmentTransactionManager")
    @ConfigurationProperties("spring.jpa")
    public PlatformTransactionManager platformTransactionManager(
            @Qualifier("departmentEntityManagerFactory")
            EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
