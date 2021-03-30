package fr.kahlouch.genetic.mating;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.mutation.Mutation;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

import java.util.ArrayList;
import java.util.List;

public class SinglePointMating<C extends Chromosome<G>, G extends Gene> extends Mating<C, G> {
    public SinglePointMating(AbstractChromosomeFactory<C, G> chromosomeFactory, Mutation<G> mutation) {
        super(chromosomeFactory, mutation);
    }

    @Override
    public List<C> doMate(C parent1, C parent2, List<C> childs) {
        int splitIndex = Constants.RANDOM_GEN.nextInt(Constants.CHROMOSOME_SIZE);
        List<G> childGene1 = new ArrayList<>();
        List<G> childGene2 = new ArrayList<>();

        childGene1.addAll(parent1.getGenes().subList(0, splitIndex));
        childGene1.addAll(parent2.getGenes().subList(splitIndex, Constants.CHROMOSOME_SIZE));

        childGene2.addAll(parent2.getGenes().subList(0, splitIndex));
        childGene2.addAll(parent1.getGenes().subList(splitIndex, Constants.CHROMOSOME_SIZE));
        mutation.mutateList(childGene1);
        mutation.mutateList(childGene2);
        childs.get(0).addGenes(childGene1);
        childs.get(1).addGenes(childGene2);
        return childs;
    }
}
