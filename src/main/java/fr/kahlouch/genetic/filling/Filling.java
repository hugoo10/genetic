package fr.kahlouch.genetic.filling;

import fr.kahlouch.genetic.algorithm.param.GeneticAlgorithmParam;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.mating.Mating;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import lombok.Setter;

import java.util.List;

@Setter
public abstract class Filling<C extends Chromosome<G>, G extends Gene> {
    protected AbstractChromosomeFactory<C, G> chromosomeFactory;
    protected Mating<C, G> mating;
    protected GeneticAlgorithmParam params;

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
