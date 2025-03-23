package fr.kahlouch.genetic.factory;

import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.population.NewBornIndividual;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class GeneticFactory {
    private final int individualChromosomeSize;
    private final Function<List<Gene>, NewBornIndividual> individualConstructor;

    protected GeneticFactory(int individualChromosomeSize, Function<List<Gene>, NewBornIndividual> individualConstructor) {
        this.individualChromosomeSize = individualChromosomeSize;
        this.individualConstructor = individualConstructor;
    }

    public abstract Gene createGene(GeneCreationInput input);

    public final NewBornIndividual createRandomIndividual() {
        final List<Gene> chromosomes = Stream.generate(() -> createGene(RandomGene.INSTANCE))
                .limit(individualChromosomeSize)
                .toList();

        return individualConstructor.apply(chromosomes);
    }

    public final NewBornIndividual createIndividualFromChromosome(List<Gene> genes) {
        return individualConstructor.apply(genes);
    }
}
