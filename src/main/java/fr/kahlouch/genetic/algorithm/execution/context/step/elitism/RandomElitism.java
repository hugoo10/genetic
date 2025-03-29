package fr.kahlouch.genetic.algorithm.execution.context.step.elitism;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomElitism<G extends Gene, I extends Individual<G, T>, T> extends Elitism<G, I, T> {

    public RandomElitism(double percent) {
        super(percent);
    }

    @Override
    public List<I> apply(List<I> individuals) {
        final var elitismSize = individuals.size() * percent;

        final List<I> elites = new ArrayList<>();
        for (int i = 0; i < elitismSize; ++i) {
            final var eliteIdx = random.nextInt(individuals.size());
            elites.add(individuals.get(eliteIdx));
        }
        return elites;
    }
}
