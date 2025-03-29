package fr.kahlouch.genetic.algorithm.helper;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public abstract class ExecutionHelper<G extends Gene, I extends Individual<G, T>, T> {
    protected final Random random = new Random(System.currentTimeMillis());

    public I createRandomIndividual(int size) {
        final var genes = Stream.generate(this::createRandomGene)
                .limit(size)
                .toList();
        return createIndividual(genes);
    }

    public abstract I createIndividual(List<G> genes);

    public abstract G breedGenes(G gene1, G gene2, double random);

    public abstract G createRandomGene();

    public G createFromGaussian(G referenceGene) {
        return createFromGaussian(referenceGene, random.nextGaussian());
    }

    protected abstract G createFromGaussian(G referenceGene, double gaussian);
}
