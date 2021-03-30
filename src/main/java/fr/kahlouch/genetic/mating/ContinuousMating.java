package fr.kahlouch.genetic.mating;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.mutation.Mutation;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

import java.util.List;

public class ContinuousMating<C extends Chromosome<G>, G extends Gene> extends Mating<C, G> {
    @Override
    public List<C> doMate(C parent1, C parent2, List<C> childs) {
        final double random = Constants.RANDOM_GEN.nextDouble();

        for (int i = 0; i < this.params.chromosomeSize; ++i) {
            List<G> genes = parent1.getGeneAt(i).breed(parent2.getGeneAt(i), random);
            childs.get(0).addGene(mutation.mutate(genes.get(0)));
            childs.get(1).addGene(mutation.mutate(genes.get(1)));
        }
        return childs;
    }
}
