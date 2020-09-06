package ru.anhot.test.kafka.config;

import org.axonframework.eventhandling.GapAwareTrackingToken;
import org.axonframework.eventhandling.TrackingToken;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class SnapshotTokenStore extends JpaTokenStore {

    @Value("${axon.token.expire-seconds}")
    int expireSeconds;

    @Autowired
    EntityManager entityManager;

    protected SnapshotTokenStore(Builder builder) {
        super(builder);
    }

    @Override
    public TrackingToken fetchToken(String processorName, int segment) {

        TrackingToken token = super.fetchToken(processorName,segment);
        try {
            long index = entityManager
                    .createQuery("select globalIndex from DomainEventEntry where function('to_timestamp', timeStamp, 'YYYY-MM-DD\\THH24:MI:SS.US') < ?1 order by globalIndex desc" , Long.class)
                    .setParameter(1, Date.from(Instant.now().minusSeconds(expireSeconds)))
                    .setMaxResults(1)
                    .getSingleResult();
            return new GapAwareTrackingToken(index , new ArrayList<Long>());
        } catch (ClassCastException e) {
            return token;
        } catch (NoResultException e) {
            return token;
        }
    }

}
