package fr.kahlouch.genetic.algorithm.execution.context.step.mutation;

import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;
import java.util.function.Function;

public interface Mutation<G extends Gene> extends Function<G, G> {
    default List<G> apply(List<G> genes) {
        return genes.stream()
                .map(this)
                .toList();
    }

    static <G extends Gene, I extends Individual<G, T>, T> Mutation<G> of(MutationType type, double odd, ExecutionHelper<G, I, T> helper) {
        return switch (type) {
            case RESET -> new ResetMutation<>(odd, helper);
            case GAUSS -> new GaussMutation<>(odd, helper);
        };
    }
}
