package ru.anhot.test.kafka.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventhandling.tokenstore.jpa.JpaTokenStore;
import org.axonframework.serialization.Serializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnapshotsConfig {
    @Bean
    TokenStore tokenStore(Serializer serializer, EntityManagerProvider entityManagerProvider) {
        return new SnapshotTokenStore(JpaTokenStore.builder()
                .serializer(serializer)
                .entityManagerProvider(entityManagerProvider));
    }
}
