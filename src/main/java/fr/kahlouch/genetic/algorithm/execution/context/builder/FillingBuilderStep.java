package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.execution.context.step.filling.FillingType;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface FillingBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    RepairBuilderStep<G, I, T> filling(FillingType type, double topIndividualsToBreedPercent);
}
