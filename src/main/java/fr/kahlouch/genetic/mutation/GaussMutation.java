package fr.kahlouch.genetic.mutation;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractGeneFactory;
import fr.kahlouch.genetic.population.Gene;

public class GaussMutation<G extends Gene> extends Mutation<G> {

    public GaussMutation(AbstractGeneFactory<G> abstractGeneFactory) {
        super(abstractGeneFactory);
    }

    @Override
    protected G doGeneMutation(G gene) {
        return abstractGeneFactory.createFromGaussian(gene, Constants.RANDOM_GEN.nextDouble());
    }
}
