package fr.kahlouch.genetic.population;

import java.util.List;


public abstract class Individual {
    private List<Gene> chromosome;
    private double fitness;

    public abstract Individual copy();

    public abstract void computeFitness();

    public List<Gene> getChromosome() {
        return chromosome;
    }

    public double getFitness() {
        return this.fitness;
    }
}
