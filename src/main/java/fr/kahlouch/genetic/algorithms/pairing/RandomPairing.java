package fr.kahlouch.genetic.algorithms.pairing;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class RandomPairing extends Pairing {
    @Override
    protected List<Individual[]> pair(List<Individual> individuals, GeneticAlgorithmParams params) {
        List<Individual[]> pairs = new ArrayList<>();
        Individual[] pair;
        while (pairs.size() < params.pairingSize / 2) {
            pair = new Individual[2];
            pair[0] = individuals.get(Constants.RANDOM_GEN.nextInt(individuals.size()));
            pair[1] = individuals.get(Constants.RANDOM_GEN.nextInt(individuals.size()));
            pairs.add(pair);
        }
        return pairs;
    }
}
