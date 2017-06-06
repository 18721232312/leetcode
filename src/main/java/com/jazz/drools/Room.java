package com.jazz.drools;

import lombok.Data;


@Data
public class Room {
    public Room(String name) {
        this.name = name;
    }

    private String name;
}
