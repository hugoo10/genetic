package fr.kahlouch.genetic.algorithm.execution.context.step.elitism;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomElitism<G extends Gene, I extends Individual<G, T>, T> implements Elitism<G, I, T> {
    private final Random random = new Random(System.currentTimeMillis());
    private final double percent;

    public RandomElitism(double percent) {
        this.percent = percent;
    }

    @Override
    public List<I> apply(List<I> individuals) {
        final var selectionSize = individuals.size() * percent;

        final List<I> elites = new ArrayList<>();
        for (int i = 0; i < selectionSize; ++i) {
            final var eliteIdx = random.nextInt(individuals.size());
            elites.add(individuals.get(eliteIdx));
        }
        return elites;
    }
}
