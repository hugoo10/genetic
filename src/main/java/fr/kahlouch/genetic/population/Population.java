package fr.kahlouch.genetic.population;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor
public final class Population {
    private final List<Individual> individuals;

    public Optional<Individual> getBest() {
        if (this.individuals != null) {
            return this.individuals
                    .parallelStream()
                    .max(Comparator.comparingDouble(Individual::getFitness));
        }
        return Optional.empty();
    }
}
