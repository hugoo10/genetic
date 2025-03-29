package fr.kahlouch.genetic.algorithm.execution.context.step.mutation;

import fr.kahlouch.genetic.algorithm.execution.context.step.ExecutionStep;
import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;

public abstract class Mutation<G extends Gene> extends ExecutionStep<G, G> {
    protected final double odd;

    protected Mutation(double odd) {
        this.odd = odd;
    }

    public List<G> apply(List<G> genes) {
        return genes.stream()
                .map(this)
                .toList();
    }

    public static <G extends Gene, I extends Individual<G, T>, T> Mutation<G> of(MutationType type, double odd, ExecutionHelper<G, I, T> helper) {
        return switch (type) {
            case RESET -> new ResetMutation<>(odd, helper);
            case GAUSS -> new GaussMutation<>(odd, helper);
        };
    }
}
