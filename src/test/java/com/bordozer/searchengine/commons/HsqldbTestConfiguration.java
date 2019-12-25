package com.bordozer.searchengine.commons;

import com.github.springtestdbunit.bean.DatabaseConfigBean;
import com.github.springtestdbunit.bean.DatabaseDataSourceConnectionFactoryBean;
import lombok.SneakyThrows;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HsqldbTestConfiguration {

    @Bean
    public DatabaseConfigBean databaseConfigBean() {
        final DatabaseConfigBean config = new DatabaseConfigBean();
        config.setDatatypeFactory(new HsqldbDataTypeFactory());
        config.setCaseSensitiveTableNames(false);
        config.setQualifiedTableNames(Boolean.TRUE);
        return config;
    }

    @Bean
    public DatabaseDataSourceConnectionFactoryBean testConnection(final DataSource dataSource, final DatabaseConfigBean databaseConfigBean) {
        final DatabaseDataSourceConnectionFactoryBean databaseDataSourceConnectionFactoryBean = new DatabaseDataSourceConnectionFactoryBean();
        databaseDataSourceConnectionFactoryBean.setDataSource(dataSource);
        databaseDataSourceConnectionFactoryBean.setDatabaseConfig(databaseConfigBean);

        return databaseDataSourceConnectionFactoryBean;
    }

    @SneakyThrows
    @Bean
    IDatabaseConnection hsqldbDataSource(final DatabaseDataSourceConnectionFactoryBean databaseDataSourceConnectionFactoryBean) {
        return databaseDataSourceConnectionFactoryBean.getObject();
    }
}
