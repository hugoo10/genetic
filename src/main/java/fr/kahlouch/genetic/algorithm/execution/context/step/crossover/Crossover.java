package fr.kahlouch.genetic.algorithm.execution.context.step.crossover;

import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.execution.context.step.selection.Parents;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;
import java.util.function.Function;

public interface Crossover<G extends Gene, I extends Individual<G, T>, T> extends Function<Parents<G, I, T>, List<List<G>>> {

    static <G extends Gene, I extends Individual<G, T>, T> Crossover<G, I, T> of(CrossoverType type, ExecutionHelper<G, I, T> helper) {
        return switch (type) {
            case CONTINUOUS -> new ContinuousCrossover<>(helper);
            case SINGLE_POINT -> new SinglePointCrossover<>();
            case TWO_POINTS -> new TwoPointsCrossover<>();
        };
    }
}
