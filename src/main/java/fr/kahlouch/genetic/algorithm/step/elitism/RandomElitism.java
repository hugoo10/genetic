package fr.kahlouch.genetic.algorithm.step.elitism;

import fr.kahlouch.genetic.algorithm.ExecutionGenerationHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomElitism<G extends Gene, I extends Individual<G, T>, T> implements Elitism<G, I, T> {
    private final Random random = new Random(System.currentTimeMillis());
    private final ExecutionGenerationHelper<G, I, T> executionGenerationHelper;

    public RandomElitism(ExecutionGenerationHelper<G, I, T> executionGenerationHelper) {
        this.executionGenerationHelper = executionGenerationHelper;
    }

    @Override
    public List<I> apply(List<I> individuals) {
        final List<I> elites = new ArrayList<>();
        for (int i = 0; i < executionGenerationHelper.eliteGroupSize(); ++i) {
            final var eliteIdx = random.nextInt(individuals.size());
            elites.add(individuals.get(eliteIdx));
        }
        return elites;
    }
}
