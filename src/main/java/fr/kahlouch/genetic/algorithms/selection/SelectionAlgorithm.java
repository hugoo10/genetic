package fr.kahlouch.genetic.algorithms.selection;

import fr.kahlouch.genetic.algorithms._genetic.GeneticAlgorithmContext;
import fr.kahlouch.genetic.population.EvaluatedIndividual;
import fr.kahlouch.genetic.utils.Constants;
import org.jspecify.annotations.Nullable;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Gatherers;
import java.util.stream.Stream;

public enum SelectionAlgorithm {
    FITTEST {
        @Override
        Stream<EvaluatedIndividual> select(List<EvaluatedIndividual> evaluatedIndividuals, GeneticAlgorithmContext context) {
            return evaluatedIndividuals.stream()
                    .sorted(Comparator.comparing(EvaluatedIndividual::fitness, Comparator.reverseOrder()));
        }
    },
    RANDOM {
        @Override
        Stream<EvaluatedIndividual> select(List<EvaluatedIndividual> evaluatedIndividuals, GeneticAlgorithmContext context) {
            return Stream.generate(() -> Constants.RANDOM_GEN.nextInt(evaluatedIndividuals.size()))
                    .map(evaluatedIndividuals::get);
        }
    },
    ROULETTE_WHEEL {
        @Override
        Stream<EvaluatedIndividual> select(List<EvaluatedIndividual> evaluatedIndividuals, GeneticAlgorithmContext context) {
            final var fitnessSum = evaluatedIndividuals.stream()
                    .mapToDouble(EvaluatedIndividual::fitness)
                    .sum();

            record TmpIndividualWithWeightedFitness(@Nullable EvaluatedIndividual individual, double weightedFitness) {
            }
            record IndividualWithWeightedFitness(EvaluatedIndividual individual, double weightedFitness) {
            }

            final var weightedIndividuals = evaluatedIndividuals.stream()
                    .sorted(Comparator.comparing(EvaluatedIndividual::fitness))
                    .gather(Gatherers.scan(() -> new TmpIndividualWithWeightedFitness(null, 0), (previous, current) ->
                            new TmpIndividualWithWeightedFitness(current, current.fitness() + previous.weightedFitness())))
                    .map(individual ->
                            new IndividualWithWeightedFitness(Objects.requireNonNull(individual.individual()), individual.weightedFitness() / fitnessSum)
                    ).toList();

            final Function<Double, EvaluatedIndividual> findMatchingIndividual = weight -> weightedIndividuals.stream()
                    .filter(individualWithWeightedFitness -> individualWithWeightedFitness.weightedFitness() > weight)
                    .map(IndividualWithWeightedFitness::individual)
                    .findFirst().orElseThrow();


            return Stream.generate(Constants.RANDOM_GEN::nextDouble)
                    .map(findMatchingIndividual);
        }
    };

    abstract Stream<EvaluatedIndividual> select(List<EvaluatedIndividual> individuals, GeneticAlgorithmContext context);
}
