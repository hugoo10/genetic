package fr.kahlouch.genetic.algorithm.execution.context.step.selection;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeightedRandomSelection<G extends Gene, I extends Individual<G, T>, T> extends Selection<G, I, T> {
    private final double percent;

    public WeightedRandomSelection(double percent) {
        this.percent = percent;
    }

    @Override
    public List<Parents<G, I, T>> apply(List<I> individuals) {
        final var selectionSize = Math.round(individuals.size() * percent);

        double[] cumulativeSum = new double[individuals.size()];
        double sum = 0;
        for (int i = 0; i < individuals.size(); ++i) {
            cumulativeSum[i] = individuals.get(i).getFitnessComputeResult().fitness();
            sum += cumulativeSum[i];
        }
        Arrays.sort(cumulativeSum);
        for (int i = 0; i < individuals.size(); ++i) {
            if (i + 1 < individuals.size()) {
                cumulativeSum[i + 1] += cumulativeSum[i];
            }
            cumulativeSum[i] = cumulativeSum[i] / sum;
        }

        List<Parents<G, I, T>> parents = new ArrayList<>();
        double randomNb;
        int i;
        while (parents.size() < selectionSize) {

            randomNb = random.nextDouble();
            i = 0;
            while (i < individuals.size() && cumulativeSum[i] < randomNb) {
                ++i;
            }
            final var parent1 = individuals.get(i);

            randomNb = random.nextDouble();
            i = 0;
            while (i < individuals.size() && cumulativeSum[i] < randomNb) {
                ++i;
            }
            final var parent2 = individuals.get(i);


            parents.add(new Parents<>(parent1, parent2));
        }
        return parents;
    }
}
