package fr.kahlouch.genetic.population;

import java.util.List;
import java.util.Objects;


public record NewBornPopulation(List<Individual> individuals) {
    public NewBornPopulation {
        Objects.requireNonNull(individuals);
    }
}
