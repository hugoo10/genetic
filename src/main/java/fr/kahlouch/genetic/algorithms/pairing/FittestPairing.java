package fr.kahlouch.genetic.algorithms.pairing;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FittestPairing extends Pairing {
    @Override
    protected List<Individual[]> pair(List<Individual> individuals, GeneticAlgorithmParams params) {
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
        List<Individual[]> pairs = new ArrayList<>();
        Individual[] pair;
        int chromosomeIndex = individuals.size() - 1;
        while (pairs.size() < params.pairingSize / 2) {
            pair = new Individual[2];
            pair[0] = individuals.get(chromosomeIndex--);
            pair[1] = individuals.get(chromosomeIndex--);
            pairs.add(pair);
        }
        return pairs;
    }
}
