package fr.kahlouch.genetic.population;

import java.util.List;

public non-sealed abstract class NewBornIndividual implements Individual {
    protected List<Gene> chromosome;

    public NewBornIndividual(List<Gene> chromosome) {
        this.chromosome = List.copyOf(chromosome);
    }

    public abstract EvaluatedIndividual computeFitness();

    @Override
    public List<Gene> getChromosome() {
        return chromosome;
    }
}
