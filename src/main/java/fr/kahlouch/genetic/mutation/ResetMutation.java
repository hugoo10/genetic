package fr.kahlouch.genetic.mutation;

import fr.kahlouch.genetic.population.Gene;

public class ResetMutation<G extends Gene> extends Mutation<G> {

    @Override
    protected G doGeneMutation(G gene) {
        return abstractGeneFactory.createRandom();
    }
}
