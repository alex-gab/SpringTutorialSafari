package com.oreilly.config;

import com.oreilly.entities.BaseballGame;
import com.oreilly.entities.Game;
import com.oreilly.entities.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@Import(InfrastructureConfig.class)
@ComponentScan(basePackages = {"com.oreilly.entities", "com.oreilly.aspects"})
@EnableAspectJAutoProxy
public class AppConfig {
    @Autowired
    private DataSource dataSource;
    @Resource
    private Team redSox;
    @Resource
    private Team cubs;

    @Bean
    @Scope("prototype")
    public Game game() {
        final BaseballGame baseballGame = new BaseballGame(redSox, cubs);
        baseballGame.setDataSource(dataSource);
        return baseballGame;
    }
}
