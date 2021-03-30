package fr.kahlouch.genetic.factory;

import fr.kahlouch.genetic.algorithm.param.GeneticAlgorithmParam;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

public abstract class AbstractChromosomeFactory<C extends Chromosome<G>, G extends Gene> {
    protected AbstractGeneFactory<G> geneFactory;
    protected GeneticAlgorithmParam params;

    public void setGeneFactory(AbstractGeneFactory<G> geneFactory) {
        this.geneFactory = geneFactory;
    }

    public void setParams(GeneticAlgorithmParam params) {
        this.params = params;
    }

    public abstract C create(Object... params);

    public final C createRandom() {
        final C chromosome = this.create();
        chromosome.setChromosomeType(Chromosome.ChromosomeType.RANDOM);
        for (int i = 0; i < this.params.chromosomeSize; ++i) {
            chromosome.addGene(this.geneFactory.createRandom());
        }
        return chromosome;
    }
}
