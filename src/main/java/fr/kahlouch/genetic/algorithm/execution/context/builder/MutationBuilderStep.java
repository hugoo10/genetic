package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.execution.context.step.mutation.MutationType;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface MutationBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    RepairBuilderStep<G, I, T> mutation(MutationType type, double odd);
}
