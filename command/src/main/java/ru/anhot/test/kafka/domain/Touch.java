package ru.anhot.test.kafka.domain;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

public class Touch {

    private UUID uuid;
    private String data;

    public Touch(String data) {
        this.uuid = UUID.randomUUID();
        this.data = data;
    }

    @TargetAggregateIdentifier
    public UUID getUuid() {
        return uuid;
    }

    public String getData() {
        return data;
    }
}
