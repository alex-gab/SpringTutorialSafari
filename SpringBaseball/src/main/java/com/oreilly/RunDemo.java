package com.oreilly;

import com.oreilly.config.AppConfig;
import com.oreilly.entities.Game;
import com.oreilly.entities.Team;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class RunDemo {
    public static void main(String[] args) {
        final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        final Game game = context.getBean("game", Game.class);
        final Team royals = context.getBean("royals", Team.class);
        final Team redSox = context.getBean("redSox", Team.class);
        final Team cubs = context.getBean("cubs", Team.class);

        game.setHomeTeam(royals);
        game.setAwayTeam(cubs);
        game.playGame();

        game.setHomeTeam(cubs);
        game.setAwayTeam(redSox);
        game.playGame();

        context.close();
    }
}
