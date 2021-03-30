package fr.kahlouch.genetic.population;

import java.util.ArrayList;
import java.util.List;

public abstract class Chromosome<G extends Gene> {
    protected ChromosomeType chromosomeType;
    protected List<G> genes;
    protected double fitness;

    public Chromosome() {
        this.genes = new ArrayList<>();
        this.fitness = 0;
    }

    public void addGene(G gene) {
        this.genes.add(gene);
    }

    public void addGenes(List<G> genes) {
        this.genes.addAll(genes);
    }

    public List<G> getGenes() {
        return genes;
    }

    public G getGeneAt(int index) {
        return this.genes.get(index);
    }


    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public abstract void computeFitness();

    @Override
    public String toString() {
        return "Chromosome{" +
                "fitness=" + fitness +
                '}';
    }

    public enum ChromosomeType {
        RANDOM, SELECTED, CHILD
    }

    public ChromosomeType getChromosomeType() {
        return chromosomeType;
    }

    public void setChromosomeType(ChromosomeType chromosomeType) {
        this.chromosomeType = chromosomeType;
    }
}
