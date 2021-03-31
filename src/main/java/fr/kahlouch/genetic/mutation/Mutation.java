package fr.kahlouch.genetic.mutation;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.algorithm.param.GeneticAlgorithmParam;
import fr.kahlouch.genetic.factory.AbstractGeneFactory;
import fr.kahlouch.genetic.population.Gene;
import lombok.Setter;

import java.util.List;

@Setter
public abstract class Mutation<G extends Gene> {
    protected AbstractGeneFactory<G> abstractGeneFactory;
    protected GeneticAlgorithmParam params;

    public final G mutate(G gene) {
        if (Constants.RANDOM_GEN.nextDouble() <= this.params.mutationOdd) {
            return doGeneMutation(gene);
        }
        return gene;
    }

    public final void mutateList(List<G> genes) {
        for (int i = 0; i < this.params.chromosomeSize; ++i) {
            genes.set(i, mutate(genes.get(i)));
        }
    }

    protected abstract G doGeneMutation(G gene);
}
