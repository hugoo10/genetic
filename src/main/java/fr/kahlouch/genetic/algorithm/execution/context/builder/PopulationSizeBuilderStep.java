package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface PopulationSizeBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    ElitismBuilderStep<G, I, T> populationSize(int size);
}
