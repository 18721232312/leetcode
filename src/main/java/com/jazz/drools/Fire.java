package com.jazz.drools;

import lombok.Data;

@Data
public class Fire {
    public Fire(Room room) {
        this.room = room;
    }

    private Room room;
}
