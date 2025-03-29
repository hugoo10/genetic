package fr.kahlouch.genetic.algorithm.execution.context.step.elitism;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.Comparator;
import java.util.List;

public class FittestElitism<G extends Gene, I extends Individual<G, T>, T> extends Elitism<G, I, T> {

    public FittestElitism(double percent) {
        super(percent);
    }

    @Override
    public List<I> apply(List<I> individuals) {
        final var elitismSize = Math.round(individuals.size() * percent);

        return individuals.stream()
                .sorted(Individual::compareTo)
                .sorted(Comparator.reverseOrder())
                .limit(elitismSize)
                .toList();
    }
}
