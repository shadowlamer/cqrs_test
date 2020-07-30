package ru.anhot.test.kafka.config;

import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Bean
    EventStore eventStore() {
        return EmbeddedEventStore.builder().storageEngine(new InMemoryEventStorageEngine()).build();
    }

}
