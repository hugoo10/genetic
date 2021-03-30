package fr.kahlouch.genetic.pairing;

import fr.kahlouch.genetic.algorithm.param.GeneticAlgorithmParam;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.List;

public abstract class Pairing<C extends Chromosome<? extends Gene>> {
    protected GeneticAlgorithmParam params;

    public void setParams(GeneticAlgorithmParam params) {
        this.params = params;
    }

    public abstract List<List<C>> pair(Generation<C> generation);
}
