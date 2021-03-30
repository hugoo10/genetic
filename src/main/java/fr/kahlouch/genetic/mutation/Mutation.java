package fr.kahlouch.genetic.mutation;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractGeneFactory;
import fr.kahlouch.genetic.population.Gene;

import java.util.List;

public abstract class Mutation<G extends Gene> {
    AbstractGeneFactory<G> abstractGeneFactory;

    Mutation(AbstractGeneFactory<G> abstractGeneFactory) {
        this.abstractGeneFactory = abstractGeneFactory;
    }

    public final G mutate(G gene) {
        if (Constants.RANDOM_GEN.nextDouble() <= Constants.MUTATION_ODD) {
            return doGeneMutation(gene);
        }
        return gene;
    }

    public final void mutateList(List<G> genes) {
        for(int i =0; i<Constants.CHROMOSOME_SIZE; ++i) {
            genes.set(i, mutate(genes.get(i)));
        }
    }

    protected abstract G doGeneMutation(G gene);
}
