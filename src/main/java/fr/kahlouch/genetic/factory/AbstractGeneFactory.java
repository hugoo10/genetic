package fr.kahlouch.genetic.factory;

import fr.kahlouch.genetic.population.Gene;

public abstract class AbstractGeneFactory<G extends Gene> {
    public abstract G create(Object... params);

    public abstract G createRandom();

    /**
     *
     * @param gene gene to mutate
     * @param gaussian number between 0 and 1
     * @return the mutated gene
     */
    public abstract G createFromGaussian(G gene, double gaussian);
}
