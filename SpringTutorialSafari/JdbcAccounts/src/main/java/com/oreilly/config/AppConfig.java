package com.oreilly.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean(name = "dataSource")
    @Profile("test")
    public DataSource dataSourceForTest() {
        return new EmbeddedDatabaseBuilder().
                generateUniqueName(true).
                setType(EmbeddedDatabaseType.H2).
                setScriptEncoding("UTF-8").
                ignoreFailedDrops(true).
                addScript("schema.sql").
                addScripts("data.sql").
                build();
    }
}
