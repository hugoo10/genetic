package fr.kahlouch.genetic.algorithm.step.mutation;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.utils.Constants;


public class GaussMutation extends Mutation {
    @Override
    protected Gene doGeneMutation(Gene gene, GeneticAlgorithmParams params) {
        return params.geneFactory.createFromGaussian(gene, Constants.RANDOM_GEN.nextDouble());
    }
}
