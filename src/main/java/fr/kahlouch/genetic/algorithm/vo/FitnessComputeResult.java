package fr.kahlouch.genetic.algorithm.vo;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public record FitnessComputeResult<T>(double fitness,
                                      @Nullable T computeData) implements Comparable<FitnessComputeResult<T>> {

    public static FitnessComputeResult<Void> of(double fitness) {
        return new FitnessComputeResult<>(fitness, null);
    }

    @Override
    public int compareTo(@NonNull FitnessComputeResult<T> other) {
        return Double.compare(this.fitness, other.fitness);
    }
}
