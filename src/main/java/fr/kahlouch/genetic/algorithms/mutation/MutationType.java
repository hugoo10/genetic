package fr.kahlouch.genetic.algorithms.mutation;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;

import java.util.List;

public enum MutationType {
    GAUSS(new GaussMutation()), RESET(new ResetMutation());

    Mutation mutation;

    MutationType(Mutation mutation) {
        this.mutation = mutation;
    }

    public Gene mutate(Gene gene, GeneticAlgorithmParams params) {
        return this.mutation.mutate(gene, params);
    }

    public void mutateList(List<Gene> genes, GeneticAlgorithmParams params) {
        this.mutation.mutateList(genes, params);
    }
}
