package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface IndividualSizeBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    PopulationSizeBuilderStep<G, I, T> individualSize(int individualSize);
}
