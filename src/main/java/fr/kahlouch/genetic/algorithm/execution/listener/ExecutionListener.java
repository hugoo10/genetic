package fr.kahlouch.genetic.algorithm.execution.listener;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import fr.kahlouch.genetic.algorithm.vo.Population;

public interface ExecutionListener<G extends Gene, I extends Individual<G, T>, T> {
    void send(Population<G, I, T> population);

    void sendEndSignal();
}
