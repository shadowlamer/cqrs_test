package ru.anhot.test.kafka.domain;

import java.util.UUID;

public class Touched {
    private UUID uuid;
    private String data;

    public Touched(Touch command) {
        this.uuid = command.getUuid();
        this.data = command.getData();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getData() {
        return data;
    }
}
