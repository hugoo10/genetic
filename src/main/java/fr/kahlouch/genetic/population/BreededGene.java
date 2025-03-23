package fr.kahlouch.genetic.population;

import java.util.Objects;

public record BreededGene(Gene gene1, Gene gene2) {
    public BreededGene {
        Objects.requireNonNull(gene1);
        Objects.requireNonNull(gene2);
    }
}
