package fr.kahlouch.genetic.algorithms.mutation;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.factory.GaussianGeneCreationInput;
import fr.kahlouch.genetic.factory.RandomGene;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.utils.Constants;

public enum MutationAlgorithm {
    GAUSS {
        @Override
        Gene mutate(Gene gene, GeneticAlgorithmContext context) {
            final var gaussianInput = new GaussianGeneCreationInput(gene, Constants.RANDOM_GEN.nextDouble());
            return context.geneticFactory().createGene(gaussianInput);
        }
    },
    RESET {
        @Override
        Gene mutate(Gene gene, GeneticAlgorithmContext context) {
            return context.geneticFactory().createGene(RandomGene.INSTANCE);
        }
    };

    abstract Gene mutate(Gene gene, GeneticAlgorithmContext context);
}
