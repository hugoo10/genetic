package fr.kahlouch.genetic.algorithms.mating;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Individual;

import java.util.List;

public enum MatingType {
    CONTINUOUS(new ContinuousMating()), SINGLE_POINT(new SinglePointMating()), TWO_POINTS(new TwoPointsMating());

    Mating mating;

    MatingType(Mating mating) {
        this.mating = mating;
    }

    public List<Gene>[] mate(Individual[] parents, GeneticAlgorithmParams params) {
        return this.mating.mate(parents, params);
    }
}
