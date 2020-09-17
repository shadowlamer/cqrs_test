package ru.anhot.test.kafka.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.apache.kafka.common.protocol.types.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class TouchSummary {

    @Id
    private UUID uuid;
    private String data;

    public TouchSummary() {
        this.uuid = UUID.randomUUID();
        this.data = new Date().toString();
    }

    public TouchSummary(UUID uuid, String data) {
        this.uuid = uuid;
        this.data = data;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
