package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.execution.context.step.crossover.CrossoverType;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface CrossoverBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    MutationBuilderStep<G, I, T> crossover(CrossoverType type);
}
