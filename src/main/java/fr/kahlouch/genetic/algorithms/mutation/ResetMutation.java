package fr.kahlouch.genetic.algorithms.mutation;


import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;

public class ResetMutation extends Mutation {
    @Override
    protected Gene doGeneMutation(Gene gene, GeneticAlgorithmParams params) {
        return params.geneFactory.createRandom();
    }
}
