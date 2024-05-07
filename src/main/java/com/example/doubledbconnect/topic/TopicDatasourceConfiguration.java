package com.example.doubledbconnect.topic;

import com.example.doubledbconnect.todo.Todo;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties("spring.datasource.topics")
//@EnableJpaRepositories(
//        basePackageClasses = Todo.class,
//        entityManagerFactoryRef = "todosEntityManagerFactory",
//        transactionManagerRef = "todosTransactionManager"
//)
public class TopicDatasourceConfiguration {

    @Bean("topicsProperties")
    public DataSourceProperties topicsDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("topicsDataSource")
    public DataSource topicsDataSource(@Qualifier("topicsProperties") DataSourceProperties topicsDataSourceProperties) {
        return topicsDataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
    }

}
