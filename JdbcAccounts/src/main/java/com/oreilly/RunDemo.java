package com.oreilly;

import com.oreilly.config.AppConfig;
import com.oreilly.services.AccountService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public final class RunDemo {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(AppConfig.class);
        context.refresh();
        System.out.println(context.getBeanDefinitionCount());

        final AccountService service = context.getBean("accountService", AccountService.class);
        service.transfer(1, 3, new BigDecimal("1.00"));

        for (String name : context.getBeanDefinitionNames()) {
            System.out.println(name);
        }
        context.close();
    }
}
