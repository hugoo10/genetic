package fr.kahlouch.genetic.factory;

import fr.kahlouch.genetic.population.Gene;

public abstract class AbstractGeneFactory {
    public abstract Gene create(Object... params);

    public abstract Gene createRandom();

    /**
     * @param gene     gene to mutate
     * @param gaussian number between 0 and 1
     * @return the mutated gene
     */
    public abstract Gene createFromGaussian(Gene gene, double gaussian);
}
