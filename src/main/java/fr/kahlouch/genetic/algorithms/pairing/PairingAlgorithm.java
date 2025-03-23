package fr.kahlouch.genetic.algorithms.pairing;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.EvaluatedIndividual;
import fr.kahlouch.genetic.population.Parents;
import fr.kahlouch.genetic.utils.Constants;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public enum PairingAlgorithm {
    FITTEST((evaluatedIndividuals, _) ->
            evaluatedIndividuals.stream()
                    .sorted(Comparator.comparingDouble(EvaluatedIndividual::fitness))
                    .gather(Gatherers.windowFixed(2))
                    .filter(list -> list.size() > 1)
                    .map(list -> new Parents(list.getFirst(), list.getLast()))
    ),
    RANDOM((evaluatedIndividuals, _) -> {
        record PairingIndex(int idx1, int idx2) {
        }

        return Stream.generate(() -> new PairingIndex(
                        Constants.RANDOM_GEN.nextInt(evaluatedIndividuals.size()),
                        Constants.RANDOM_GEN.nextInt(evaluatedIndividuals.size())))
                .map(pairingIndex -> {
                    final var parent1 = evaluatedIndividuals.get(pairingIndex.idx1());
                    final var parent2 = evaluatedIndividuals.get(pairingIndex.idx2());
                    return new Parents(parent1, parent2);
                });
    }),
    WEIGHTED_RANDOM((evaluatedIndividuals, _) -> {
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


        record LimitWeights(double weight1, double weight2) {
        }

        return Stream.generate(() -> new LimitWeights(Constants.RANDOM_GEN.nextDouble(), Constants.RANDOM_GEN.nextDouble()))
                .map(limitWeights -> {
                    final var parent1 = findMatchingIndividual.apply(limitWeights.weight1);
                    final var parent2 = findMatchingIndividual.apply(limitWeights.weight2);
                    return new Parents(parent1, parent2);
                });
    });

    private final BiFunction<List<EvaluatedIndividual>, GeneticAlgorithmContext, Stream<Parents>> algorithm;

    PairingAlgorithm(BiFunction<List<EvaluatedIndividual>, GeneticAlgorithmContext, Stream<Parents>> algorithm) {
        this.algorithm = algorithm;
    }

    Stream<Parents> pair(List<EvaluatedIndividual> individuals, GeneticAlgorithmContext context) {
        return this.algorithm.apply(individuals, context);
    }
}
