package fr.kahlouch.genetic.algorithms.mating;


import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.Children;
import fr.kahlouch.genetic.population.Parents;

import java.util.Objects;

public record Mating(MatingAlgorithm algorithm) {
    private static final MatingAlgorithm DEFAULT_ALGORITHM = MatingAlgorithm.TWO_POINTS;

    public Mating {
        Objects.requireNonNull(algorithm);
    }

    public static  Mating of() {
        return new Mating(DEFAULT_ALGORITHM);
    }


    public Children mate(Parents parents, GeneticAlgorithmContext context) {
        return this.algorithm.mate(parents, context);
    }
}
