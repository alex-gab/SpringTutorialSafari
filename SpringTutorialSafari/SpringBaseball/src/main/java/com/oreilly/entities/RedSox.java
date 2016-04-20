package com.oreilly.entities;

import org.springframework.stereotype.Component;

@Component
public final class RedSox implements Team {
    public final String getName() {
        return "Boston Red Sox";
    }

    @Override
    public final String toString() {
        return getName();
    }
}
