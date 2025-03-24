package fr.kahlouch.genetic.algorithm.step.elitism;

import fr.kahlouch.genetic.algorithm.ExecutionGenerationHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.Comparator;
import java.util.List;

public class FittestElitism<G extends Gene, I extends Individual<G, T>, T> implements Elitism<G, I, T> {
    private final ExecutionGenerationHelper<G, I, T> executionGenerationHelper;

    public FittestElitism(ExecutionGenerationHelper<G, I, T> executionGenerationHelper) {
        this.executionGenerationHelper = executionGenerationHelper;
    }

    @Override
    public List<I> apply(List<I> individuals) {
        return individuals.stream()
                .sorted(Individual::compareTo)
                .sorted(Comparator.reverseOrder())
                .limit(this.executionGenerationHelper.eliteGroupSize())
                .toList();
    }
}
