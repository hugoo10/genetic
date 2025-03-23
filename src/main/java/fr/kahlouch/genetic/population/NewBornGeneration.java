package fr.kahlouch.genetic.population;

import java.util.List;
import java.util.Objects;


public record NewBornGeneration(long generationNumber, List<Individual> individuals) {
    public NewBornGeneration {
        Objects.requireNonNull(individuals);
    }
}
