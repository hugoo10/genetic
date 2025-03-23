package fr.kahlouch.genetic.population;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public final class EvaluatedPopulation {
    private final List<EvaluatedIndividual> individuals;
    private final EvaluatedIndividual best;

    public EvaluatedPopulation(List<EvaluatedIndividual> individuals) {
        Objects.requireNonNull(individuals);
        this.individuals = List.copyOf(individuals);
        this.best = this.individuals.stream()
                .max(Comparator.comparingDouble(EvaluatedIndividual::fitness))
                .orElseThrow();
    }

    public List<EvaluatedIndividual> getIndividuals() {
        return individuals;
    }

    public EvaluatedIndividual getBest() {
        return this.best;
    }
}
