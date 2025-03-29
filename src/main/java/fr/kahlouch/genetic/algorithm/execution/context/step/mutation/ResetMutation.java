package fr.kahlouch.genetic.algorithm.execution.context.step.mutation;


import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public class ResetMutation<G extends Gene, I extends Individual<G, T>, T> extends Mutation<G> {
    private final ExecutionHelper<G, I, T> executionHelper;

    public ResetMutation(double odd, ExecutionHelper<G, I, T> executionHelper) {
        super(odd);
        this.executionHelper = executionHelper;
    }

    @Override
    public G apply(G gene) {
        if (random.nextDouble() > odd) return gene;
        return executionHelper.createRandomGene();
    }
}
