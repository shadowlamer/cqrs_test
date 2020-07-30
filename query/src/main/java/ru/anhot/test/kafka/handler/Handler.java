package ru.anhot.test.kafka.handler;

import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("query")
public class Handler {
    @EventHandler
    public void handle (EventMessage<?> event) {
        System.out.println(event.toString());
    }
}
