package fr.kahlouch.genetic.algorithm.helper;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;

public abstract class ExecutionHelper<G extends Gene, I extends Individual<G, T>, T> {
    public abstract I createIndividual(List<G> genes);

    public abstract G breedGenes(G gene1, G gene2, double random);

    public G createGeneFromCommand(CreateGeneCommand<G> createGeneCommand) {
        return switch (createGeneCommand) {
            case CreateGeneRandomCommand _ -> createRandomGene();
            case CreateGeneFromGaussianCommand<G> command ->
                    createFromGaussian(command.getReferenceGene(), command.getGaussian());
            case CreateGeneCutomCommand command -> createGene(command);
        };
    }

    protected abstract G createRandomGene();

    protected abstract G createFromGaussian(G referenceGene, double gaussian);

    protected abstract G createGene(CreateGeneCutomCommand command);
}
