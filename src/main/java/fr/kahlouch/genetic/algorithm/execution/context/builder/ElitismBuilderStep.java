package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.execution.context.step.elitism.ElitismType;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface ElitismBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    SelectionBuilderStep<G, I, T> elitism(ElitismType type, double percent);
}
