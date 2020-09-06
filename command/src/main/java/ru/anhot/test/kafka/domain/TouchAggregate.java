package ru.anhot.test.kafka.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.DomainEventData;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.messaging.Message;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateCreationPolicy.CREATE_IF_MISSING;
import static org.axonframework.modelling.command.AggregateCreationPolicy.NEVER;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate(snapshotTriggerDefinition = "eachThirdSnapshotTrigger", filterEventsByType = true)
public class TouchAggregate implements Serializable {

    @AggregateIdentifier
    private UUID uuid;
    private String data;

    public TouchAggregate() {}

    public TouchAggregate(Touch command) {
        handle(command);
    }

    @CommandHandler
    @CreationPolicy(CREATE_IF_MISSING)
    public void handle(Touch command) {
        this.uuid = command.getUuid();
        this.data = command.getData();
        Touched event = new Touched(command);
        apply(event);
    }

    @EventSourcingHandler
    void handler(Touched event) {
        this.uuid = event.getUuid();
        this.data = event.getData();
    }

    @EventSourcingHandler
    void logHandler(EventMessage<?> message) {
        System.out.println(message.toString());
    }

}
