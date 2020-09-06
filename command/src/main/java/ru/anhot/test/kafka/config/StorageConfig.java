//package ru.anhot.test.kafka.config;
//
//import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
//import org.axonframework.eventsourcing.eventstore.SequenceEventStorageEngine;
//import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine;
//import org.axonframework.eventsourcing.eventstore.jpa.JpaEventStorageEngine;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class StorageConfig {
//    @Bean
//    EventStorageEngine eventStorageEngine() {
//        return new SequenceEventStorageEngine(new JpaEventStorageEngine(), new InMemoryEventStorageEngine());
//    }
//
//}
