package fr.kahlouch.genetic.algorithms.selection;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.EvaluatedIndividual;
import fr.kahlouch.genetic.utils.Constants;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public enum SelectionAlgorithm {
    FITTEST((evaluatedIndividuals, _) -> evaluatedIndividuals.stream()
            .sorted(Comparator.comparing(EvaluatedIndividual::fitness, Comparator.reverseOrder()))),
    RANDOM((evaluatedIndividuals, _) ->
            Stream.generate(() -> Constants.RANDOM_GEN.nextInt(evaluatedIndividuals.size()))
                    .map(evaluatedIndividuals::get)),
    ROULETTE_WHEEL((evaluatedIndividuals, _) -> {
        final var fitnessSum = evaluatedIndividuals.stream()
                .mapToDouble(EvaluatedIndividual::fitness)
                .sum();

        record IndividualWithWeightedFitness(EvaluatedIndividual individual, double weightedFitness) {
        }

        final var weightedIndividuals = evaluatedIndividuals.stream()
                .sorted(Comparator.comparing(EvaluatedIndividual::fitness))
                .gather(Gatherers.scan(() -> new IndividualWithWeightedFitness(null, 0), (previous, current) ->
                        new IndividualWithWeightedFitness(current, current.fitness() + previous.weightedFitness())))
                .map(individual ->
                        new IndividualWithWeightedFitness(individual.individual(), individual.weightedFitness()/ fitnessSum)
                ).toList();

        final Function<Double, EvaluatedIndividual> findMatchingIndividual = weight -> weightedIndividuals.stream()
                .filter(individualWithWeightedFitness -> individualWithWeightedFitness.weightedFitness() > weight)
                .map(IndividualWithWeightedFitness::individual)
                .findFirst().orElseThrow();


        return Stream.generate(Constants.RANDOM_GEN::nextDouble)
                .map(findMatchingIndividual);
    });

    private final BiFunction<List<EvaluatedIndividual>, GeneticAlgorithmContext, Stream<EvaluatedIndividual>> algorithm;

    SelectionAlgorithm(BiFunction<List<EvaluatedIndividual>, GeneticAlgorithmContext, Stream<EvaluatedIndividual>> algorithm) {
        this.algorithm = algorithm;
    }

    Stream<EvaluatedIndividual> select(List<EvaluatedIndividual> individuals, GeneticAlgorithmContext context) {
        return this.algorithm.apply(individuals, context);
    }
}
