package fr.kahlouch.genetic.algorithms.selection;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RouletteWheelSelection extends Selection {

    @Override
    protected List<Individual> select(List<Individual> individuals, GeneticAlgorithmParams params) {
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
        double[] cumulativeSum = new double[individuals.size()];
        double sum = 0;
        for (int i = 0; i < individuals.size(); ++i) {
            cumulativeSum[i] = individuals.get(i).getFitness();
            sum += cumulativeSum[i];
        }
        for (int i = 0; i < individuals.size(); ++i) {
            if (i + 1 < individuals.size()) {
                cumulativeSum[i + 1] += cumulativeSum[i];
            }
            cumulativeSum[i] = cumulativeSum[i] / sum;
        }
        List<Individual> selected = new ArrayList<>();
        double random;
        while (selected.size() < params.selectionSize) {
            random = Constants.RANDOM_GEN.nextDouble();
            int i = 0;
            while (i < individuals.size() && cumulativeSum[i] < random) {
                ++i;
            }
            if (i < individuals.size()) {
                selected.add(individuals.get(i));
            }
        }
        return selected;
    }
}
