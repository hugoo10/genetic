package fr.kahlouch.genetic.algorithms.pairing;


import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.EvaluatedIndividual;
import fr.kahlouch.genetic.population.Parents;

import java.util.List;
import java.util.Objects;

public record Pairing(PairingAlgorithm algorithm, int size) {
    private static final PairingAlgorithm DEFAULT_ALGORITHM = PairingAlgorithm.FITTEST;
    private static final int DEFAULT_SIZE = 1;

    public Pairing {
        Objects.requireNonNull(algorithm);
    }

    public static Pairing of() {
        return new Pairing(DEFAULT_ALGORITHM, DEFAULT_SIZE);
    }

    public static Pairing of(PairingAlgorithm algorithm) {
        return new Pairing(algorithm, DEFAULT_SIZE);
    }

    public static Pairing of(int size) {
        return new Pairing(DEFAULT_ALGORITHM, size);
    }


    public List<Parents> pair(List<EvaluatedIndividual> parents, GeneticAlgorithmContext context) {
        return this.algorithm.pair(parents, context).limit(this.size).toList();
    }
}
