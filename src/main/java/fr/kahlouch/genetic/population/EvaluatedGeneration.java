package fr.kahlouch.genetic.population;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public final class EvaluatedGeneration {
    private final long generationNumber;
    private final List<EvaluatedIndividual> individuals;
    private final EvaluatedIndividual best;

    public EvaluatedGeneration(long generationNumber, List<EvaluatedIndividual> individuals) {
        Objects.requireNonNull(individuals);
        this.generationNumber = generationNumber;
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

    public long getGenerationNumber() {
        return generationNumber;
    }

    @Override
    public String toString() {
        return this.generationNumber + ": " + this.best;
    }
}
