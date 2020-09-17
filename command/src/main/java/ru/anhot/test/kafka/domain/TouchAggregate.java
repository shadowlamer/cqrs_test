package ru.anhot.test.kafka.domain;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.CreationPolicy;
import org.axonframework.spring.stereotype.Aggregate;

import java.io.Serializable;
import java.util.UUID;

import static org.axonframework.modelling.command.AggregateCreationPolicy.CREATE_IF_MISSING;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Slf4j
@Aggregate(snapshotTriggerDefinition = "eachThirdSnapshotTrigger")
public class TouchAggregate implements Serializable {

    @AggregateIdentifier
    private UUID uuid;
    private String data;

    public TouchAggregate() {
        log.info("TouchAggregate created");
    }

    public TouchAggregate(Touch command) {
        handle(command);
    }

    @CommandHandler
    @CreationPolicy(CREATE_IF_MISSING)
    public void handle(Touch command) {
        log.info("Command handled: " + command.toString());
        this.uuid = command.getUuid();
        this.data = command.getData();
        Touched event = new Touched(command);
        apply(event);
    }

    @EventSourcingHandler
    void handler(Touched event) {
        this.uuid = event.getUuid();
        this.data = event.getData();
        log.info("Touch event handled " + event.toString());
        log.info("Aggregate data is: " + this.data + "now");
    }

}
