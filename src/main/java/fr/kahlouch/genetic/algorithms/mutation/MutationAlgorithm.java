package fr.kahlouch.genetic.algorithms.mutation;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.factory.GaussianGeneCreationInput;
import fr.kahlouch.genetic.factory.RandomGene;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.utils.Constants;

import java.util.function.BiFunction;

public enum MutationAlgorithm {
    GAUSS((gene, context) -> {
        final var gaussianInput = new GaussianGeneCreationInput<>(gene, Constants.RANDOM_GEN.nextDouble());
        return context.geneticFactory().createGene(gaussianInput);
    }),
    RESET((_, context) ->
            context.geneticFactory().createGene(RandomGene.INSTANCE));

    private final BiFunction<Gene, GeneticAlgorithmContext, Gene> algorithm;

    MutationAlgorithm(BiFunction<Gene, GeneticAlgorithmContext, Gene> algorithm) {
        this.algorithm = algorithm;
    }

    Gene mutate(Gene gene, GeneticAlgorithmContext context) {
        return this.algorithm.apply(gene, context);
    }
}
