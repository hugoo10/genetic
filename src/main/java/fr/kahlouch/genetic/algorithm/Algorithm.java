package fr.kahlouch.genetic.algorithm;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import fr.kahlouch.genetic.algorithm.vo.Population;
import org.jspecify.annotations.Nullable;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public final class Algorithm<G extends Gene, I extends Individual<G, T>, T> {
    private final Population<G, I, T> firstPopulation;

    public Algorithm(List<I> individuals) {
        Objects.requireNonNull(individuals);
        this.firstPopulation = Population.firstPopulation(individuals);
    }

    public I findBest(ExecutionGeneration<G, I, T> executionGeneration, ExecutionLimit executionLimit, @Nullable List<ExecutionListener<G, I, T>> executionListeners) {
        Objects.requireNonNull(executionGeneration);
        Objects.requireNonNull(executionLimit);
        final List<ExecutionListener<G, I, T>> listeners = Objects.requireNonNullElseGet(executionListeners, List::of);

        final var startInstant = Instant.now();
        I currentBest = null;
        var populationToCompute = firstPopulation;


        do {
            populationToCompute.compute();

            final var contender = populationToCompute.getBest();
            if (currentBest == null || contender.compareTo(currentBest) >= 0) {
                currentBest = contender;
            }

            for (var listener : listeners) {
                listener.send(populationToCompute);
            }

            final var nextIndividuals = executionGeneration.apply(populationToCompute.getIndividuals());
            populationToCompute = populationToCompute.nextPopulation(nextIndividuals);

        } while (executionLimit.isEnd(currentBest.getFitnessComputeResult().fitness(), startInstant));

        return currentBest;
    }
}
