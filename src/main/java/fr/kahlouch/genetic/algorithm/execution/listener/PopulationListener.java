package fr.kahlouch.genetic.algorithm.execution.listener;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import fr.kahlouch.genetic.algorithm.vo.Population;

public final class PopulationListener<G extends Gene, I extends Individual<G, T>, T> implements ExecutionListener<G, I, T> {
    private Population<G, I, T> currentPopulation;


    @Override
    public void send(Population<G, I, T> population) {
        this.currentPopulation = population;
    }

    @Override
    public void sendEndSignal() {
//noop
    }

    public Population<G, I, T> getBestPopulation() {
        return this.currentPopulation;
    }
}
