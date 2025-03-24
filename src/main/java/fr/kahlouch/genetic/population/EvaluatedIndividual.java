package fr.kahlouch.genetic.population;

import java.util.List;
import java.util.Objects;

public record EvaluatedIndividual(NewBornIndividual individual, double fitness) implements Individual {

    public EvaluatedIndividual {
        Objects.requireNonNull(individual);
    }

    @Override
    public List<Gene> getChromosome() {
        return this.individual.getChromosome();
    }

    @Override
    public String toString() {
        return "EvaluatedIndividual{" +
                "fitness=" + fitness +
                '}';
    }
}
