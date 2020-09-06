package ru.anhot.test.kafka.config;

import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackingEventProcessorConfiguration;
import org.axonframework.messaging.StreamableMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class EventsConfig {

    @Autowired
    public void registerPublisherToEventProcessor(
            EventProcessingConfigurer eventProcessingConfigurer) {

        eventProcessingConfigurer
                .registerTrackingEventProcessorConfiguration(
                        config -> TrackingEventProcessorConfiguration
                                .forSingleThreadedProcessing()
                                .andInitialSegmentsCount(1)
                                .andInitialTrackingToken(StreamableMessageSource::createHeadToken)
                )
                .assignProcessingGroup(
                        (processingGroup) -> (getPrefix() + "_" + processingGroup))
                        ;
    }

    public static String getPrefix() {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            hostname = "localhost";
        }
        return hostname;
    }
}
