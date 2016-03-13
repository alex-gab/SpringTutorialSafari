package com.oreilly;

import com.oreilly.entities.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

@Configuration
@Import(InfrastructureConfig.class)
public class AppConfig {
    @Bean
    public Game game(final DataSource dataSource) {
        final BaseballGame baseballGame = new BaseballGame(redSox(), cubs());
        baseballGame.setDataSource(dataSource);
        return baseballGame;
    }

    @Bean
    public Team redSox() {
        return new RedSox();
    }

    @Bean
    public Team cubs() {
        return new Cubs();
    }
}
