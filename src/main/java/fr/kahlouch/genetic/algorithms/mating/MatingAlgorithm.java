package fr.kahlouch.genetic.algorithms.mating;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.Children;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Parents;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;

public enum MatingAlgorithm {
    CONTINUOUS {
        @Override
        Children mate(Parents parents, GeneticAlgorithmContext context) {
            final double random = Constants.RANDOM_GEN.nextDouble();
            final var child1Genes = new ArrayList<Gene>();
            final var child2Genes = new ArrayList<Gene>();


            for (int i = 0; i < parents.parent1().getChromosome().size(); ++i) {
                final var gene1 = parents.parent1().getChromosome().get(i);
                final var gene2 = parents.parent2().getChromosome().get(i);

                final var breedGenes = gene1.breed(gene2, random);
                child1Genes.add(breedGenes.gene1());
                child2Genes.add(breedGenes.gene2());
            }

            final var child1 = context.geneticFactory().createIndividualFromChromosome(child1Genes);
            final var child2 = context.geneticFactory().createIndividualFromChromosome(child2Genes);

            return new Children(child1, child2);
        }
    },
    SINGLE_POINT {
        @Override
        Children mate(Parents parents, GeneticAlgorithmContext context) {
            final var parentChromosome1 = parents.parent1().getChromosome();
            final var parentChromosome2 = parents.parent2().getChromosome();

            final var splitIndex = Constants.RANDOM_GEN.nextInt(parentChromosome1.size());

            final var genes1 = new ArrayList<Gene>();
            final var genes2 = new ArrayList<Gene>();

            genes1.addAll(parentChromosome1.subList(0, splitIndex));
            genes1.addAll(parentChromosome2.subList(splitIndex, parentChromosome2.size()));

            genes2.addAll(parentChromosome2.subList(0, splitIndex));
            genes2.addAll(parentChromosome1.subList(splitIndex, parentChromosome1.size()));

            final var child1 = context.mutation().mutateAndCreateIndividual(genes1, context);
            final var child2 = context.mutation().mutateAndCreateIndividual(genes2, context);

            return new Children(child1, child2);
        }
    },
    TWO_POINTS {
        @Override
        Children mate(Parents parents, GeneticAlgorithmContext context) {
            final var parentChromosome1 = parents.parent1().getChromosome();
            final var parentChromosome2 = parents.parent2().getChromosome();

            final int splitIndex1 = Constants.RANDOM_GEN.nextInt(parentChromosome1.size());
            final int splitIndex2 = Constants.RANDOM_GEN.nextInt(parentChromosome1.size() - splitIndex1) + splitIndex1;

            final var genes1 = new ArrayList<Gene>();
            final var genes2 = new ArrayList<Gene>();

            genes1.addAll(parentChromosome1.subList(0, splitIndex1));
            genes1.addAll(parentChromosome2.subList(splitIndex1, splitIndex2));
            genes1.addAll(parentChromosome1.subList(splitIndex2, parentChromosome1.size()));

            genes2.addAll(parentChromosome2.subList(0, splitIndex1));
            genes2.addAll(parentChromosome1.subList(splitIndex1, splitIndex2));
            genes2.addAll(parentChromosome2.subList(splitIndex2, parentChromosome2.size()));

            final var child1 = context.mutation().mutateAndCreateIndividual(genes1, context);
            final var child2 = context.mutation().mutateAndCreateIndividual(genes2, context);

            return new Children(child1, child2);
        }
    };

    abstract Children mate(Parents parents, GeneticAlgorithmContext context);
}
