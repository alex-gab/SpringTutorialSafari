package com.oreilly;

import com.oreilly.config.AppConfig;
import com.oreilly.repositories.AccountRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public final class RunDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(AppConfig.class);
        context.refresh();
        System.out.println(context.getBeanDefinitionCount());

        final AccountRepository repository = context.getBean("jdbcAccountRepository", AccountRepository.class);
        repository.createAccount(new BigDecimal("1500.00"));
//        repository.deleteAccount(4L);

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.close();
    }
}
