package com.jazz.drools;

import lombok.Data;


@Data
public class Sprinkler {
    public Sprinkler(Room room) {
        this.room = room;
    }

    private Room room;
    private boolean on;
}
