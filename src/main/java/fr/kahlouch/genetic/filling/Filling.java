package fr.kahlouch.genetic.filling;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.mating.Mating;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

import java.util.List;

public abstract class Filling<C extends Chromosome<G>, G extends Gene> {
    protected AbstractChromosomeFactory<C, G> chromosomeFactory;
    protected Mating<C, G> mating;

    public Filling(AbstractChromosomeFactory<C, G> chromosomeFactory, Mating<C, G> mating) {
        this.chromosomeFactory = chromosomeFactory;
        this.mating = mating;
    }

    public final void fill(List<C> futurGeneration) {
        while (futurGeneration.size() < Constants.POPULATION_SIZE) {
            List<C> chromosomes = doFill(futurGeneration);
            int it = 0;
            while (futurGeneration.size() < Constants.POPULATION_SIZE && it < chromosomes.size()) {
                futurGeneration.add(chromosomes.get(it++));
            }
        }
    }

    abstract List<C> doFill(List<C> futurGeneration);
}
