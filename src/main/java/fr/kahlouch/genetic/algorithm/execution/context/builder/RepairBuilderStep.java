package fr.kahlouch.genetic.algorithm.execution.context.builder;

import fr.kahlouch.genetic.algorithm.execution.context.ExecutionContext;
import fr.kahlouch.genetic.algorithm.execution.context.step.repair.Repair;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public interface RepairBuilderStep<G extends Gene, I extends Individual<G, T>, T> {
    ExecutionContext<G, I, T> repair(Repair<G> repair);
}
