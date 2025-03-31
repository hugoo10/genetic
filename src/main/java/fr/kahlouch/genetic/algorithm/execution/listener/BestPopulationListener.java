package fr.kahlouch.genetic.algorithm.execution.listener;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import fr.kahlouch.genetic.algorithm.vo.Population;

public final class BestPopulationListener<G extends Gene, I extends Individual<G, T>, T> implements ExecutionListener<G, I, T> {
    private Population<G, I, T> currentBestPopulation;


    @Override
    public void send(Population<G, I, T> population) {
        final var contenderIndividual = population.getBest();

        if (currentBestPopulation == null) {
            currentBestPopulation = population;
            return;
        }

        final var currentBestIndividual = currentBestPopulation.getBest();
        if (currentBestIndividual.compareTo(contenderIndividual) < 0) {
            currentBestPopulation = population;
        }
    }


    @Override
    public void sendEndSignal() {
//noop
    }

    public Population<G, I, T> getBestPopulation() {
        return currentBestPopulation;
    }

}
