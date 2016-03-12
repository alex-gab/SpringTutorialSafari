package com.oreilly;

import com.oreilly.entities.Game;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public final class RunDemo {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        final Game game = context.getBean("game", Game.class);
        System.out.println(game.playGame());
    }
}
