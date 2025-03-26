package fr.kahlouch.genetic.algorithm.execution.context.step.selection;

import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;
import java.util.function.Function;

public interface Selection<G extends Gene, I extends Individual<G, T>, T> extends Function<List<I>, List<Parents<G, I, T>>> {

    static <G extends Gene, I extends Individual<G, T>, T> Selection<G, I, T> of(SelectionType type, double percent) {
        return switch (type) {
            case FITTEST -> new FittestSelection<>(percent);
            case RANDOM -> new RandomSelection<>(percent);
            case WEIGHTED_RANDOM -> new WeightedRandomSelection<>(percent);
        };
    }
}
