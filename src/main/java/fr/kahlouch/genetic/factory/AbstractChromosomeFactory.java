package fr.kahlouch.genetic.factory;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

public abstract class AbstractChromosomeFactory<C extends Chromosome<G>, G extends Gene> {
    protected final AbstractGeneFactory<G> geneFactory;

    public AbstractChromosomeFactory(AbstractGeneFactory<G> geneFactory) {
        this.geneFactory = geneFactory;
    }

    public abstract C create(Object... params);

    public final C createRandom() {
        final C chromosome = this.create();
        chromosome.setChromosomeType(Chromosome.ChromosomeType.RANDOM);
        for (int i = 0; i < Constants.CHROMOSOME_SIZE; ++i) {
            chromosome.addGene(this.geneFactory.createRandom());
        }
        return chromosome;
    }
}
