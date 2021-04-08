package fr.kahlouch.genetic.population;

import java.util.List;


public abstract class Individual {
    protected List<Gene> chromosome;
    protected double fitness;

    public Individual(List<Gene> chromosome) {
        this.chromosome = chromosome;
    }

    public abstract void computeFitness();

    public List<Gene> getChromosome() {
        return chromosome;
    }

    public double getFitness() {
        return this.fitness;
    }
}
