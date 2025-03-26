package fr.kahlouch.genetic.algorithm.execution.context.step.mutation;


import fr.kahlouch.genetic.algorithm.helper.CreateGeneRandomCommand;
import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

public class ResetMutation<G extends Gene, I extends Individual<G, T>, T> implements Mutation<G> {
    private final double odd;
    private final ExecutionHelper<G, I, T> executionHelper;

    public ResetMutation(double odd, ExecutionHelper<G, I, T> executionHelper) {
        this.odd = odd;
        this.executionHelper = executionHelper;
    }

    @Override
    public G apply(G gene) {
        return (G) executionHelper.createGeneFromCommand(CreateGeneRandomCommand.INSTANCE);
    }
}
