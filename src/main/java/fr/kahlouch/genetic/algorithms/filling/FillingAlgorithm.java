package fr.kahlouch.genetic.algorithms.filling;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.EvaluatedIndividual;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.population.Parents;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public enum FillingAlgorithm {
    RANDOM((_, context) ->
            Stream.generate(() -> context.geneticFactory().createRandomIndividual())),
    RANDOM_BREED_BEST((selected, context) -> {
        final var toBreed = selected.stream()
                .sorted(Comparator.comparing(EvaluatedIndividual::fitness, Comparator.reverseOrder()))
                .limit(context.filling().retrieveTopSize())
                .toList();

        if (toBreed.isEmpty()) {
            return RANDOM.fill(selected, context);
        }

        return Stream.generate(() -> toBreed)
                .flatMap(List::stream)
                .flatMap(parent1 -> {
                    final var parent2 = context.geneticFactory().createRandomIndividual();
                    final var parents = new Parents(parent1, parent2);
                    return context.mating().mate(parents, context).stream();
                });
    });

    private final BiFunction<List<EvaluatedIndividual>, GeneticAlgorithmContext, Stream<Individual>> algorithm;

    FillingAlgorithm(BiFunction<List<EvaluatedIndividual>, GeneticAlgorithmContext, Stream<Individual>> algorithm) {
        this.algorithm = algorithm;
    }

    Stream<Individual> fill(List<EvaluatedIndividual> selected, GeneticAlgorithmContext context) {
        return algorithm.apply(selected, context);
    }
}
