package fr.kahlouch.genetic.algorithm;

import org.jspecify.annotations.Nullable;

import java.time.Duration;
import java.time.Instant;

public record ExecutionLimit(@Nullable Double fitness, @Nullable Duration timeout) {
    public ExecutionLimit {
        if (fitness == null && timeout == null) {
            throw new IllegalArgumentException("At least one of fitness and timeout should be filled");
        }
    }

    public boolean isEnd(double bestFitness, Instant startInstant) {
        if (timeout != null) {
            final var now = Instant.now();
            final var currentDuration = Duration.between(startInstant, now);

            if (currentDuration.compareTo(timeout) >= 0) return true;
        }

        if (fitness != null) {
            return fitness <= bestFitness;
        }

        return false;
    }
}
