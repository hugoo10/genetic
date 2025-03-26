package fr.kahlouch.genetic.algorithm.helper;

import fr.kahlouch.genetic.algorithm.vo.Gene;

import java.util.Random;

public final class CreateGeneFromGaussianCommand<G extends Gene> implements CreateGeneCommand {
    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private final G referenceGene;
    private final double gaussian;

    public CreateGeneFromGaussianCommand(G referenceGene) {
        this.referenceGene = referenceGene;
        this.gaussian = RANDOM.nextGaussian();
    }

    public G getReferenceGene() {
        return referenceGene;
    }

    public double getGaussian() {
        return gaussian;
    }
}
