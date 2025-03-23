package fr.kahlouch.genetic.algorithms.filling;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.EvaluatedIndividual;
import fr.kahlouch.genetic.population.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public record Filling(FillingAlgorithm algorithm, int retrieveTopSize) {
    private static final FillingAlgorithm DEFAULT_ALGORITHM = FillingAlgorithm.RANDOM_BREED_BEST;
    private static final int DEFAULT_RETRIEVE_TOP_SIZE = 1;

    public Filling {
        Objects.requireNonNull(algorithm);
    }

    public static Filling of() {
        return new Filling(DEFAULT_ALGORITHM, DEFAULT_RETRIEVE_TOP_SIZE);
    }

    public static Filling of(FillingAlgorithm algorithm) {
        return new Filling(algorithm, DEFAULT_RETRIEVE_TOP_SIZE);
    }

    public static Filling of(int retrieveTopSize) {
        return new Filling(DEFAULT_ALGORITHM, retrieveTopSize);
    }

    public List<Individual> fill(List<Individual> population, List<EvaluatedIndividual> selected, GeneticAlgorithmContext context) {
        final var missingIndividuals = context.populationSize() - population.size();
        if (missingIndividuals <= 0) {
            return population;
        }

        final var newPopulation = new ArrayList<>(population);
        final var additionalIndividuals = this.algorithm.fill(selected, context).limit(missingIndividuals).toList();
        newPopulation.addAll(additionalIndividuals);

        return newPopulation;
    }
}
