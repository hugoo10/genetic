package fr.kahlouch.genetic.algorithms._genetic;

import fr.kahlouch.genetic.algorithms.filling.Filling;
import fr.kahlouch.genetic.algorithms.mating.Mating;
import fr.kahlouch.genetic.algorithms.mutation.Mutation;
import fr.kahlouch.genetic.algorithms.pairing.Pairing;
import fr.kahlouch.genetic.algorithms.selection.Selection;
import fr.kahlouch.genetic.factory.GeneticFactory;
import fr.kahlouch.genetic.population.*;
import org.jspecify.annotations.Nullable;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public final class GeneticAlgorithm {
    private final GeneticAlgorithmContext context;

    GeneticAlgorithm(int populationSize,
                     GeneticFactory geneticFactory,
                     Selection selection,
                     Pairing pairing,
                     Mating mating,
                     Mutation mutation,
                     Filling filling) {
        this.context = new GeneticAlgorithmContext(populationSize, geneticFactory, selection, pairing, mating, mutation, filling);
    }

    public static GeneticAlgorithmBuilder builder(int populationSize, GeneticFactory geneticFactory) {
        return new GeneticAlgorithmBuilder(populationSize, geneticFactory);
    }

    public GeneticAlgorithmExecutionHistory compute(GeneticAlgorithmExecutionCommand command) {
        final var history = new GeneticAlgorithmExecutionHistory(command.getHistoryType());
        final var startTime = Instant.now();
        NewBornGeneration currentPopulation = command.getFirstPopulation();

        do {
            if (!isInTime(startTime, command.getTimeCap())) {
                break;
            }
            final var evaluatedPopulation = computeFitness(currentPopulation);
            currentPopulation = generateNextGeneration(evaluatedPopulation);
            history.historize(evaluatedPopulation);
        } while (history.getCurrentBest().fitness() < command.getFitnessCap());

        return history;
    }

    private boolean isInTime(Instant startTime, @Nullable Duration timeCap) {
        if (timeCap == null) {
            return true;
        }
        return Duration.between(startTime, Instant.now()).compareTo(timeCap) < 0;
    }


    private EvaluatedGeneration computeFitness(NewBornGeneration population) {
        final var evaluatedIndividuals = population.individuals().stream()
                .map(individual ->
                        switch (individual) {
                            case EvaluatedIndividual ei -> ei;
                            case NewBornIndividual nbi -> nbi.computeFitness();
                        }
                ).toList();
        return new EvaluatedGeneration(population.generationNumber(), evaluatedIndividuals);
    }


    private NewBornGeneration generateNextGeneration(EvaluatedGeneration previousPopulation) {
        final var individuals2Mate = this.context.pairing().pair(previousPopulation.getIndividuals(), context);

        final List<Individual> newPopulation = individuals2Mate.stream()
                .flatMap(parents -> this.context.mating().mate(parents, context).stream())
                .collect(Collectors.toList());

        final var selectedIndividuals = this.context.selection().select(previousPopulation.getIndividuals(), context);
        newPopulation.addAll(selectedIndividuals);

        final var newBornPopulation = this.context.filling().fill(newPopulation, selectedIndividuals, context);
        return new NewBornGeneration(previousPopulation.getGenerationNumber() + 1, newBornPopulation);
    }
}
