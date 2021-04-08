package fr.kahlouch.genetic.algorithms.mutation;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.utils.Constants;

import java.util.List;

public abstract class Mutation {


    final Gene mutate(Gene gene, GeneticAlgorithmParams params) {
        if (Constants.RANDOM_GEN.nextDouble() <= params.mutationOdd) {
            return doGeneMutation(gene, params);
        }
        return gene;
    }

    final void mutateList(List<Gene> genes, GeneticAlgorithmParams params) {
        for (int i = 0; i < genes.size(); ++i) {
            genes.set(i, mutate(genes.get(i), params));
        }
    }

    protected abstract Gene doGeneMutation(Gene gene, GeneticAlgorithmParams params);
}
