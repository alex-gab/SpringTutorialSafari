package com.oreilly.entities;

import org.springframework.stereotype.Component;

@Component
public final class Royals implements Team {
    public final String getName() {
        return "Kansas City Royals";
    }
}
