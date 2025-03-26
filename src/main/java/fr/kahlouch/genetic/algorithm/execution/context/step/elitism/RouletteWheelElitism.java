package fr.kahlouch.genetic.algorithm.execution.context.step.elitism;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RouletteWheelElitism<G extends Gene, I extends Individual<G, T>, T> implements Elitism<G, I, T> {
    private final Random random = new Random(System.currentTimeMillis());
    private final double percent;

    public RouletteWheelElitism(double percent) {
        this.percent = percent;
    }

    @Override
    public List<I> apply(List<I> individuals) {
        final var selectionSize = individuals.size() * percent;


        final var otherIndividuals = new ArrayList<>(individuals);
        otherIndividuals.sort(Individual::compareTo);

        double[] cumulativeSum = new double[otherIndividuals.size()];
        double sum = 0;
        for (int i = 0; i < otherIndividuals.size(); ++i) {
            cumulativeSum[i] = otherIndividuals.get(i).getFitnessComputeResult().fitness();
            sum += cumulativeSum[i];
        }
        for (int i = 0; i < otherIndividuals.size(); ++i) {
            if (i + 1 < otherIndividuals.size()) {
                cumulativeSum[i + 1] += cumulativeSum[i];
            }
            cumulativeSum[i] = cumulativeSum[i] / sum;
        }
        List<I> selected = new ArrayList<>();
        double randomNumber;
        while (selected.size() < selectionSize) {
            randomNumber = random.nextDouble();
            int i = 0;
            while (i < otherIndividuals.size() && cumulativeSum[i] < randomNumber) {
                ++i;
            }
            if (i < otherIndividuals.size()) {
                selected.add(otherIndividuals.get(i));
            }
        }
        return selected;
    }
}
