package com.example.doubledbconnect.todo;

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

import javax.sql.DataSource;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties("spring.datasource.todos")
@EnableJpaRepositories(
        basePackageClasses = Todo.class,
        entityManagerFactoryRef = "todosEntityManagerFactory",
        transactionManagerRef = "todosTransactionManager"
)
public class TodoJpaConfiguration {

    @Bean("todosEntityManager")
    public LocalContainerEntityManagerFactoryBean todosEntityManagerFactory(
            @Qualifier("todosDataSource") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(dataSource)
                .packages(Todo.class)
                .build();
    }

    @Bean("todosProperties")
    @Primary
    public DataSourceProperties todosDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("todosDataSource")
    @Primary
    public DataSource todosDataSource(@Qualifier("todosProperties") DataSourceProperties todosDataSourceProperties) {
        return todosDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

    @Bean("todosEntityManager")
    public JpaTransactionManager todosTransactionManager(
            @Qualifier("todosEntityManager") EntityManagerFactory todosEntityManager) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(todosEntityManager);
        return transactionManager;
    }
}
