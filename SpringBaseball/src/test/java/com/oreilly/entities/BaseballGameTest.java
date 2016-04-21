package com.oreilly.entities;

import com.oreilly.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public final class BaseballGameTest {
    @Autowired
    private Game game;
    @Autowired
    private ApplicationContext ctx;

    @Test
    @DirtiesContext
    public void testGetAndSetHomeTeam() {
        final Team royals = ctx.getBean("royals", Team.class);
        game.setHomeTeam(royals);
        assertEquals(royals.getName(), game.getHomeTeam().getName());
    }

    @Test
    @DirtiesContext
    public final void testPlayGame() throws Exception {
        final String home = game.getHomeTeam().getName();
        final String away = game.getAwayTeam().getName();
        final String result = game.playGame();

        assertTrue(result.contains(home) || result.contains(away));
    }
}