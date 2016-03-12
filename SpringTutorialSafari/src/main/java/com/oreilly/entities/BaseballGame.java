package com.oreilly.entities;

public final class BaseballGame implements Game {
    private Team homeTeam;
    private Team awayTeam;

    public BaseballGame(final Team homeTeam, final Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public final void setHomeTeam(final Team team) {
        this.homeTeam = team;
    }

    public final Team getHomeTeam() {
        return homeTeam;
    }

    public final void setAwayTeam(final Team team) {
        this.awayTeam = team;
    }

    public final Team getAwayTeam() {
        return awayTeam;
    }

    public final String playGame() {
        return Math.random() < 0.5 ? getHomeTeam().getName() : getAwayTeam().getName();
    }
}
