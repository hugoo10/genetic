package fr.kahlouch.genetic.algorithm.execution.listener;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import fr.kahlouch.genetic.algorithm.vo.Population;

import java.util.ArrayList;
import java.util.List;

public final class BestIndividualHistory<G extends Gene, I extends Individual<G, T>, T> implements ExecutionListener<G, I, T> {
    private final List<I> indivuals;

    public BestIndividualHistory() {
        this.indivuals = new ArrayList<>();
    }

    @Override
    public void send(Population<G, I, T> population) {
        final var contender = population.getBest();

        if (indivuals.isEmpty()) {
            indivuals.add(contender);
            return;
        }

        final var currentBest = indivuals.getLast();
        if (currentBest.compareTo(contender) <= 0) {
            indivuals.add(contender);
        }
    }
}
