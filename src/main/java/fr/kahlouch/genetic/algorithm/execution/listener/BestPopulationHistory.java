package fr.kahlouch.genetic.algorithm.execution.listener;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import fr.kahlouch.genetic.algorithm.vo.Population;

import java.util.ArrayList;
import java.util.List;

public final class BestPopulationHistory<G extends Gene, I extends Individual<G, T>, T> implements ExecutionListener<G, I, T> {
    private final List<Population<G, I, T>> populations;

    public BestPopulationHistory() {
        this.populations = new ArrayList<>();
    }

    @Override
    public void send(Population<G, I, T> population) {
        final var contenderIndividual = population.getBest();

        if (populations.isEmpty()) {
            populations.add(population);
            return;
        }

        final var currentBestIndividual = populations.getLast().getBest();
        if (currentBestIndividual.compareTo(contenderIndividual) <= 0) {
            populations.add(population);
        }
    }
}
