package fr.kahlouch.genetic.algorithms.pairing;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;

import java.util.List;

public enum PairingType {
    FITTEST(new FittestPairing()),
    RANDOM(new RandomPairing()),
    WEIGHTED_RANDOM(new WeightedRandomPairing());

    Pairing pairing;

    PairingType(Pairing pairing) {
        this.pairing = pairing;
    }

    public List<Individual[]> pair(List<Individual> individuals, GeneticAlgorithmParams params) {
        return this.pairing.pair(individuals, params);
    }
}
