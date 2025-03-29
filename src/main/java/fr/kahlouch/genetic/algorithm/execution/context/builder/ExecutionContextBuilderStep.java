package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface ExecutionContextBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    IndividualSizeBuilderStep<G, I, T> helper(ExecutionHelper<G, I, T> helper);
}
