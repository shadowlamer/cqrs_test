package ru.anhot.test.kafka.handler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.springframework.stereotype.Component;
import ru.anhot.test.kafka.domain.Touched;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@ProcessingGroup("query")
@AllowReplay
public class TouchProjection {

    private static final String EVENT_CLASS_NAME = "ru.anhot.test.kafka.domain.Touched";

    private Map<UUID, String> objects = new HashMap<>();

    @AggregateIdentifier
    private UUID uuid;
    private String data;

    public TouchProjection() {
        System.out.println("TouchProjection created");
    }

    @EventSourcingHandler
    public void handleEvent(Touched event) {
        System.out.println("Touch event handled. Data changed to " +
                event.getData() + " for uuid " + event.getUuid());
        objects.put(event.getUuid(), event.getData());
    }

    public String getDataByUuid(UUID uuid) {
        return objects.get(uuid);
    }

    public Map<UUID, String> getAllData() {
        return objects;
    }
}
