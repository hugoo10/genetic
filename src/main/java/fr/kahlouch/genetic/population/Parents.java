package fr.kahlouch.genetic.population;

import java.util.Objects;

public record Parents(Individual parent1, Individual parent2) {
    public Parents {
        Objects.requireNonNull(parent1);
        Objects.requireNonNull(parent2);
    }
}
