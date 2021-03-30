package fr.kahlouch.genetic.filling;

import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.mating.Mating;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

import java.util.Collections;
import java.util.List;

public class RandomFilling<C extends Chromosome<G>, G extends Gene> extends Filling<C, G> {
    public RandomFilling(AbstractChromosomeFactory<C, G> chromosomeFactory, Mating<C, G> mating, int populationSize) {
        super(chromosomeFactory, mating, populationSize);
    }

    @Override
    List<C> doFill(List<C> futurGeneration) {
        return Collections.singletonList(this.chromosomeFactory.createRandom());
    }
}
