package fr.kahlouch.genetic.pairing;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightedRandomPairing<C extends Chromosome<? extends Gene>> implements Pairing<C> {
    public List<List<C>> pair(Generation<C> generation) {
        double[] cumulativeSum = new double[Constants.POPULATION_SIZE];
        double sum = 0;
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i) {
            cumulativeSum[i] = generation.getChromosomes().get(i).getFitness();
            sum += cumulativeSum[i];
        }
        Arrays.sort(cumulativeSum);
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i) {
            if (i + 1 < Constants.POPULATION_SIZE) {
                cumulativeSum[i + 1] += cumulativeSum[i];
            }
            cumulativeSum[i] = cumulativeSum[i] / sum;
        }

        List<List<C>> pairs = new ArrayList<>();
        List<C> pair;
        double random;
        int i;
        while (pairs.size() < Constants.PAIRING_SIZE / 2) {
            pair = new ArrayList<>();

            random = Constants.RANDOM_GEN.nextDouble();
            i = 0;
            while (i < Constants.POPULATION_SIZE && cumulativeSum[i] < random) {
                ++i;
            }
            if (i < Constants.POPULATION_SIZE) {
                pair.add(generation.getChromosomes().get(i));
            }

            random = Constants.RANDOM_GEN.nextDouble();
            i = 0;
            while (i < Constants.POPULATION_SIZE && cumulativeSum[i] < random) {
                ++i;
            }
            if (i < Constants.POPULATION_SIZE) {
                pair.add(generation.getChromosomes().get(i));
            }

            pairs.add(pair);
        }
        return pairs;
    }
}
