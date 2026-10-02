package com.financial.loansystem.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public record IdempotencyKey(
    @NotBlank(message = "Operation cannot be blank")
    @Size(max = 50, message = "Operation must be at most 50 characters")
    String operation,

    @NotBlank(message = "Channel cannot be blank")
    @Size(max = 20, message = "Channel must be at most 20 characters")
    String channel
) {
    public IdempotencyKey {
        Objects.requireNonNull(operation, "Operation cannot be null");
        Objects.requireNonNull(channel, "Channel cannot be null");
    }

    public String toCompositeKey() {
        return operation + "#" + channel;
    }

    public static IdempotencyKey fromCompositeKey(String compositeKey) {
        String[] parts = compositeKey.split("#");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid composite key format");
        }
        return new IdempotencyKey(parts[0], parts[1]);
    }
}