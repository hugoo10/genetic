package fr.kahlouch.genetic.algorithms.selection;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.EvaluatedIndividual;

import java.util.List;
import java.util.Objects;

public record Selection(SelectionAlgorithm algorithm, int size) {
    private static final SelectionAlgorithm DEFAULT_ALGORITHM = SelectionAlgorithm.FITTEST;
    private static final int DEFAULT_SIZE = 1;

    public Selection {
        Objects.requireNonNull(algorithm);
    }

    public static  Selection of() {
        return new Selection(DEFAULT_ALGORITHM, DEFAULT_SIZE);
    }

    public static  Selection of(SelectionAlgorithm algorithm) {
        return new Selection(algorithm, DEFAULT_SIZE);
    }

    public static  Selection of(int size) {
        return new Selection(DEFAULT_ALGORITHM, size);
    }

    public List<EvaluatedIndividual> select(List<EvaluatedIndividual> evaluatedIndividuals, GeneticAlgorithmContext context) {
        return this.algorithm.select(evaluatedIndividuals, context)
                .limit(this.size)
                .toList();
    }
}
