package com.example.doubledbconnect.todo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackageClasses = Todo.class,
        entityManagerFactoryRef = "todosEntityManagerFactory",
        transactionManagerRef = "todosTransactionManager"
)
@ConfigurationProperties("spring.datasource.todos")
public class TodoDatasourceConfiguration {

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

//    @Bean
//    public DataSource todosDataSource() {
//        return todosDataSourceProperties()
//                .initializeDataSourceBuilder()
//                .build();
//    }
}
