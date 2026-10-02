package com.financial.loansystem.infrastructure.config;

import com.financial.loansystem.domain.model.IdempotencyKey;
import com.financial.loansystem.domain.port.LoanRequestRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Configuration
public class IdempotencyConfig {

    private static final Duration IDEMPOTENCY_TTL = Duration.ofHours(24);

    @Bean
    public ReactiveStringRedisTemplate reactiveStringRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        return new ReactiveStringRedisTemplate(connectionFactory);
    }

    @Bean
    public ReactiveRedisTemplate<String, String> reactiveRedisTemplate(
            ReactiveRedisConnectionFactory connectionFactory) {
        StringRedisSerializer serializer = new StringRedisSerializer();
        RedisSerializationContext<String, String> context = RedisSerializationContext
                .<String, String>newSerializationContext(serializer)
                .key(serializer)
                .value(serializer)
                .hashKey(serializer)
                .hashValue(serializer)
                .build();
        return new ReactiveRedisTemplate<>(connectionFactory, context);
    }

    @Bean
    public IdempotencyService idempotencyService(
            ReactiveRedisTemplate<String, String> redisTemplate,
            LoanRequestRepository loanRequestRepository) {
        return new IdempotencyService(redisTemplate, loanRequestRepository);
    }

    public static class IdempotencyService {

        private final ReactiveRedisTemplate<String, String> redisTemplate;
        private final LoanRequestRepository loanRequestRepository;

        public IdempotencyService(ReactiveRedisTemplate<String, String> redisTemplate,
                                   LoanRequestRepository loanRequestRepository) {
            this.redisTemplate = redisTemplate;
            this.loanRequestRepository = loanRequestRepository;
        }

        public Mono<IdempotencyCheck> checkAndStore(IdempotencyKey key, UUID requestId) {
            String compositeKey = key.toCompositeKey();

            return redisTemplate.hasKey(compositeKey)
                    .flatMap(exists -> {
                        if (Boolean.TRUE.equals(exists)) {
                            String storedRequestId = redisTemplate.opsForValue().get(compositeKey).block();
                            return Mono.just(new IdempotencyCheck(
                                    true,
                                    storedRequestId != null ? UUID.fromString(storedRequestId) : null,
                                    "Ya existe una solicitud con esta clave de idempotencia"
                            ));
                        }
                        return redisTemplate.opsForValue()
                                .set(compositeKey, requestId.toString(), IDEMPOTENCY_TTL)
                                .then(Mono.just(new IdempotencyCheck(false, null, null)));
                    });
        }

        public Mono<Boolean> existsInDatabase(IdempotencyKey key) {
            return loanRequestRepository.existsByIdempotencyKey(key);
        }

        public record IdempotencyCheck(boolean isDuplicate, UUID existingRequestId, String message) {}
    }
}