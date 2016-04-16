package com.oreilly;

import com.oreilly.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

public final class RunDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                AppConfig.class, AppConfig.class);
        System.out.println(context.getBeanDefinitionCount());

        final DataSource dataSource = context.getBean("dataSource", DataSource.class);

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }
}
