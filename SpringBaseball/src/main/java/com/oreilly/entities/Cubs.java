package com.oreilly.entities;

import org.springframework.stereotype.Component;

@Component
public final class Cubs implements Team {
    public final String getName() {
        return "Chicago Cubs";
    }

    @Override
    public final String toString() {
        return getName();
    }
}
