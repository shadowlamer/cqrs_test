package ru.anhot.test.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.GapAwareTrackingToken;
import org.axonframework.eventhandling.TrackingToken;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.extensions.kafka.eventhandling.consumer.streamable.KafkaTrackingToken;
import org.springframework.beans.factory.annotation.Autowired;
import ru.anhot.test.kafka.domain.TouchProjection;

import javax.persistence.EntityManager;
import java.util.ArrayList;

@Slf4j
public class SnapshotTokenStore extends JpaTokenStore {

    @Autowired
    EntityManager entityManager;

    @Autowired
    TouchProjection touchProjection;

    protected SnapshotTokenStore(Builder builder) {
        super(builder);
    }


    @Override
    public void storeToken(TrackingToken token, String processorName, int segment) {
        log.info("Token " + token + " stored for " + processorName);
        super.storeToken(token, processorName, segment);
    }

    @Override
    public TrackingToken fetchToken(String processorName, int segment) {
        TrackingToken token = super.fetchToken(processorName, segment);

        if (0 == touchProjection.getSummariesCount()) {
            log.info("No stored states found. Reset tokens and replaying all events.");
            // If there is no stored token found return null
            if (null == token) return null;
            // Reset KafkaTrackingToken
            if (KafkaTrackingToken.class.equals(token.getClass())) {
                return KafkaTrackingToken.emptyToken();
            }
            // Reset GapAwareTrackingToken
            if (GapAwareTrackingToken.class.equals(token.getClass())) {
                return new GapAwareTrackingToken(0L, new ArrayList<Long>());
            }
        }
        log.info("Token " + token + " fetched for " + processorName);
        return token;
    }

}
