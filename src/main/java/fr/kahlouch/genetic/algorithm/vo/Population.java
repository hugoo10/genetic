package fr.kahlouch.genetic.algorithm.vo;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public final class Population<G extends Gene, I extends Individual<G, T>, T> {
    private static final Logger LOGGER = Logger.getLogger(Population.class.getSimpleName());

    private final long id;
    private final List<I> individuals;
    private I best;

    private Population(long id, List<I> individuals) {
        Objects.requireNonNull(individuals);
        this.id = id;
        this.individuals = List.copyOf(individuals);
        this.best = null;
    }

    public static <G extends Gene, I extends Individual<G, T>, T> Population<G, I, T> firstPopulation(List<I> individuals) {
        return new Population<>(1, individuals);
    }

    public Population<G, I, T> nextPopulation(List<I> individuals) {
        return new Population<>(this.id + 1, individuals);
    }

    public Population<G, I, T> compute() {
        if (this.best != null) return this;

        this.best = this.individuals.parallelStream().unordered()
                .peek(Individual::evaluate)
                .max(Individual::compareTo)
                .orElseThrow();
        return this;
    }

    public long getId() {
        return id;
    }

    public List<I> getIndividuals() {
        return individuals;
    }

    public I getBest() {
        return best;
    }
}
