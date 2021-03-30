package fr.kahlouch.genetic.filling;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.algorithm.param.GeneticAlgorithmParam;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.mating.Mating;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

import java.util.List;

public abstract class Filling<C extends Chromosome<G>, G extends Gene> {
    protected AbstractChromosomeFactory<C, G> chromosomeFactory;
    protected Mating<C, G> mating;
    protected GeneticAlgorithmParam params;

    public void setChromosomeFactory(AbstractChromosomeFactory<C, G> chromosomeFactory) {
        this.chromosomeFactory = chromosomeFactory;
    }

    public void setMating(Mating<C, G> mating) {
        this.mating = mating;
    }

    public void setParams(GeneticAlgorithmParam params) {
        this.params = params;
    }

    public final void fill(List<C> futurGeneration) {
        while (futurGeneration.size() < this.params.populationSize) {
            List<C> chromosomes = doFill(futurGeneration);
            int it = 0;
            while (futurGeneration.size() < this.params.populationSize && it < chromosomes.size()) {
                futurGeneration.add(chromosomes.get(it++));
            }
        }
    }

    abstract List<C> doFill(List<C> futurGeneration);
}
