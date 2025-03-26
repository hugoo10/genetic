package fr.kahlouch.genetic.algorithm.execution.context.step.selection;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightedRandomSelection<G extends Gene, I extends Individual<G, T>, T> implements Selection<G,I,T> {
    private final double percent;

    public WeightedRandomSelection(double percent) {
        this.percent = percent;
    }

    @Override
    public List<Parents<G, I, T>> apply(List<I> is) {
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
