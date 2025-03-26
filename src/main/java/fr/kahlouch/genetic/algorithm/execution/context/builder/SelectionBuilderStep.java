package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.execution.context.step.selection.SelectionType;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface SelectionBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    CrossoverBuilderStep<G, I, T> random(SelectionType selectionType, double percent);
}
