package com.oreilly;

import com.oreilly.entities.*;

public final class RunDemoWithoutSpring {
    public static void main(String[] args) {
        final Team redSox = new RedSox();
        final Team cubs = new Cubs();
        Game game = new BaseballGame(redSox, cubs);
        System.out.println(game.playGame());
    }
}
