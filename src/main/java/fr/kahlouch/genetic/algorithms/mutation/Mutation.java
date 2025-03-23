package fr.kahlouch.genetic.algorithms.mutation;


import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.population.NewBornIndividual;
import fr.kahlouch.genetic.utils.Constants;

import java.util.List;
import java.util.Objects;

public record Mutation(MutationAlgorithm algorithm, double odd) {
    private static final MutationAlgorithm DEFAULT_ALGORITHM = MutationAlgorithm.RESET;
    private static final double DEFAULT_ODD = 0.01;

    public Mutation {
        Objects.requireNonNull(algorithm);
    }

    public static Mutation of() {
        return new Mutation(DEFAULT_ALGORITHM, DEFAULT_ODD);
    }

    public static Mutation of(MutationAlgorithm algorithm) {
        return new Mutation(algorithm, DEFAULT_ODD);
    }

    public static Mutation of(double odd) {
        return new Mutation(DEFAULT_ALGORITHM, odd);
    }


    public NewBornIndividual mutateAndCreateIndividual(List<Gene> genes, GeneticAlgorithmContext context) {
        final var mutatedGenes = mutate(genes, context);
        return context.geneticFactory().createIndividualFromChromosome(mutatedGenes);
    }

    public List<Gene> mutate(List<Gene> genes, GeneticAlgorithmContext context) {
        return genes.stream()
                .map(gene -> this.mutate(gene, context))
                .toList();
    }

    private Gene mutate(Gene gene, GeneticAlgorithmContext context) {
        if (Constants.RANDOM_GEN.nextDouble() <= this.odd) {
            return algorithm.mutate(gene, context);
        }
        return gene;
    }
}
