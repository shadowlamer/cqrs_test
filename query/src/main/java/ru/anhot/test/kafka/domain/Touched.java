package ru.anhot.test.kafka.domain;

import java.util.UUID;

public class Touched {
    private UUID uuid;
    private String data;

    public Touched() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getData() {
        return data;
    }
}
