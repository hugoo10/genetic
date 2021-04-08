package fr.kahlouch.genetic.algorithms.pairing;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightedRandomPairing extends Pairing {
    @Override
    protected List<Individual[]> pair(List<Individual> individuals, GeneticAlgorithmParams params) {
        double[] cumulativeSum = new double[individuals.size()];
        double sum = 0;
        for (int i = 0; i < individuals.size(); ++i) {
            cumulativeSum[i] = individuals.get(i).getFitness();
            sum += cumulativeSum[i];
        }
        Arrays.sort(cumulativeSum);
        for (int i = 0; i < individuals.size(); ++i) {
            if (i + 1 < individuals.size()) {
                cumulativeSum[i + 1] += cumulativeSum[i];
            }
            cumulativeSum[i] = cumulativeSum[i] / sum;
        }

        List<Individual[]> pairs = new ArrayList<>();
        Individual[] pair;
        double random;
        int i;
        while (pairs.size() < params.pairingSize / 2) {
            pair = new Individual[2];

            random = Constants.RANDOM_GEN.nextDouble();
            i = 0;
            while (i < individuals.size() && cumulativeSum[i] < random) {
                ++i;
            }
            if (i < individuals.size()) {
                pair[0] = individuals.get(i);
            }

            random = Constants.RANDOM_GEN.nextDouble();
            i = 0;
            while (i < individuals.size() && cumulativeSum[i] < random) {
                ++i;
            }
            if (i < individuals.size()) {
                pair[1] = individuals.get(i);
            }

            pairs.add(pair);
        }
        return pairs;
    }
}
