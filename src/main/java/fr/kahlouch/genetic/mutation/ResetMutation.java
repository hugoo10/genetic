package fr.kahlouch.genetic.mutation;

import fr.kahlouch.genetic.factory.AbstractGeneFactory;
import fr.kahlouch.genetic.population.Gene;

public class ResetMutation<G extends Gene> extends Mutation<G> {

    public ResetMutation(AbstractGeneFactory<G> abstractGeneFactory) {
        super(abstractGeneFactory);
    }

    @Override
    protected G doGeneMutation(G gene) {
        return abstractGeneFactory.createRandom();
    }
}
