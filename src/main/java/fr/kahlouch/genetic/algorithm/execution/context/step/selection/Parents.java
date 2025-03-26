package fr.kahlouch.genetic.algorithm.execution.context.step.selection;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.Objects;

public record Parents<G extends Gene, I extends Individual<G, T>, T>(I individual1, I individual2) {

    public Parents {
        Objects.requireNonNull(individual1);
        Objects.requireNonNull(individual2);
    }
}
