package fr.kahlouch.genetic.algorithm.vo;

import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class Individual<G extends Gene, T> implements Comparable<Individual<G, T>> {
    protected final UUID id;
    protected final List<G> genes;
    protected FitnessComputeResult<T> fitnessComputeResult;

    protected Individual(List<G> genes) {
        Objects.requireNonNull(genes);

        this.id = UUID.randomUUID();
        this.genes = List.copyOf(genes);
        this.fitnessComputeResult = null;
    }

    public void evaluate() {
        if (this.fitnessComputeResult != null) return;
        this.fitnessComputeResult = computeFitness();
    }

    protected abstract FitnessComputeResult<T> computeFitness();

    @Override
    public int compareTo(@NonNull Individual<G, T> other) {
        if (this.fitnessComputeResult == null && other.fitnessComputeResult == null) return 0;
        if (this.fitnessComputeResult == null) return -1;
        if (other.fitnessComputeResult == null) return 1;

        return this.fitnessComputeResult.compareTo(other.fitnessComputeResult);
    }

    public FitnessComputeResult<T> getFitnessComputeResult() {
        return fitnessComputeResult;
    }

    public UUID getId() {
        return id;
    }

    public List<G> getGenes() {
        return genes;
    }
}


