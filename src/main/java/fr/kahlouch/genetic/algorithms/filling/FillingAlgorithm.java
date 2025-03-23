package fr.kahlouch.genetic.algorithms.filling;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.EvaluatedIndividual;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.population.Parents;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public enum FillingAlgorithm {
    RANDOM {
        @Override
        Stream<Individual> fill(List<EvaluatedIndividual> selected, GeneticAlgorithmContext context) {
            return Stream.generate(() -> context.geneticFactory().createRandomIndividual());
        }
    },
    RANDOM_BREED_BEST {
        @Override
        Stream<Individual> fill(List<EvaluatedIndividual> selected, GeneticAlgorithmContext context) {
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
        }
    };

    abstract Stream<Individual> fill(List<EvaluatedIndividual> selected, GeneticAlgorithmContext context);
}
