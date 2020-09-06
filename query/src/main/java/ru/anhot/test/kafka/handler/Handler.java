package ru.anhot.test.kafka.handler;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.CannotResolveClassException;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.AllowReplay;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.serialization.UnknownSerializedType;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@ProcessingGroup("query")
@AllowReplay
public class Handler {

    private static final String EVENT_CLASS_NAME = "ru.anhot.test.kafka.domain.Touched";
    private final EventProcessingConfiguration eventProcessingConfiguration;

    public Handler(EventProcessingConfiguration eventProcessingConfiguration) {
        this.eventProcessingConfiguration = eventProcessingConfiguration;
    }

    public class QueryTouched {
        private UUID uuid;
        private String data;
        public UUID getUuid() {
            return uuid;
        }
        public String getData() {
            return data;
        }
    }

    @EventSourcingHandler
    public void handleEvent (EventMessage<?> event) {
        try {
            System.out.println(event.toString());
            UnknownSerializedType payload = (UnknownSerializedType) event.getPayload();
            try {
                String xml = payload.readData(String.class);
                XStream xstream = new XStream();
                xstream.alias(EVENT_CLASS_NAME, QueryTouched.class);
                QueryTouched newEvent = (QueryTouched) xstream.fromXML(xml);
                System.out.println("Touched. UUID:" + newEvent.uuid.toString());
            } catch (CannotResolveClassException e) {
                System.out.println("Unknown event " + payload.serializedType().getName());
            }
        } catch (ClassCastException e) {
            System.out.println("Unhandled event " + event.getPayloadType());
        }
    }
}
