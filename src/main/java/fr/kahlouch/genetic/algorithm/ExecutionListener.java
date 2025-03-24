package fr.kahlouch.genetic.algorithm;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Population;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface ExecutionListener<G extends Gene, I extends Individual<G, T>, T> {
    void send(Population<G, I, T> population);
}
