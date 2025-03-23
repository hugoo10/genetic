package fr.kahlouch.genetic.algorithms._genetic;

import fr.kahlouch.genetic.algorithms.filling.Filling;
import fr.kahlouch.genetic.algorithms.mating.Mating;
import fr.kahlouch.genetic.algorithms.mutation.Mutation;
import fr.kahlouch.genetic.algorithms.pairing.Pairing;
import fr.kahlouch.genetic.algorithms.selection.Selection;
import fr.kahlouch.genetic.factory.GeneticFactory;
import fr.kahlouch.genetic.population.*;
import fr.kahlouch.genetic.utils.HistoryType;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class GeneticAlgorithm {
    private final GeneticAlgorithmContext context;

    private final List<Integer> bestLineage;
    private final List<EvaluatedPopulation> lineage;
    @Nullable
    private EvaluatedIndividual currentBest;

    GeneticAlgorithm(int populationSize,
                     GeneticFactory geneticFactory,
                     Selection selection,
                     Pairing pairing,
                     Mating mating,
                     Mutation mutation,
                     Filling filling) {
        this.context = new GeneticAlgorithmContext(populationSize, geneticFactory, selection, pairing, mating, mutation, filling);
        this.bestLineage = new ArrayList<>();
        this.lineage = new ArrayList<>();
    }

    public static GeneticAlgorithmBuilder builder(int populationSize, GeneticFactory geneticFactory) {
        return new GeneticAlgorithmBuilder(populationSize, geneticFactory);
    }

    public EvaluatedPopulation compute(NewBornPopulation population, HistoryType historyType, Double fitnessCap, Long timeCapInMillis) {
        long time = System.currentTimeMillis();
        NewBornPopulation currentPopulation = population;
        boolean haveTime;

        var generation = 0;
        do {
            final var evaluatedPopulation = computeFitness(currentPopulation, generation, historyType);
            haveTime = timeCapInMillis == null || System.currentTimeMillis() - time < timeCapInMillis;
            if (haveTime) {
                currentPopulation = generateNextGeneration(evaluatedPopulation);
            }
            ++generation;
        } while (haveTime && (this.currentBest == null || this.currentBest.fitness() < fitnessCap));

        return this.lineage.getLast();
    }


    private EvaluatedPopulation computeFitness(NewBornPopulation population, int generation, HistoryType historyType) {
        final var evaluatedIndividuals = population.individuals().stream()
                .map(individual ->
                        switch (individual) {
                            case EvaluatedIndividual ei -> ei;
                            case NewBornIndividual nbi -> nbi.computeFitness();
                        }
                ).toList();
        final var evaluatedPopulation = new EvaluatedPopulation(evaluatedIndividuals);
        final var evaluatedBest = evaluatedPopulation.getBest();

        if (historyType == HistoryType.FULL) {
            this.lineage.add(evaluatedPopulation);
        }
        final var newBest = currentBest == null || currentBest.fitness() < evaluatedBest.fitness();
        if (newBest) {
            this.currentBest = evaluatedBest;
        }
        if ((newBest && historyType == HistoryType.ONLY_BEST) || historyType == HistoryType.FULL) {
            this.lineage.add(evaluatedPopulation);
            if (newBest) {
                this.bestLineage.add(generation);
                System.err.println((generation + 1) + " :: " + this.currentBest.toString());
            }
        }
        return evaluatedPopulation;
    }


    private NewBornPopulation generateNextGeneration(EvaluatedPopulation previousPopulation) {
        final var individuals2Mate = this.context.pairing().pair(previousPopulation.getIndividuals(), context);

        final List<Individual> newPopulation = individuals2Mate.stream()
                .flatMap(parents -> this.context.mating().mate(parents, context).stream())
                .collect(Collectors.toList());

        final var selectedIndividuals = this.context.selection().select(previousPopulation.getIndividuals(), context);
        newPopulation.addAll(selectedIndividuals);

        final var newBornPopulation = this.context.filling().fill(newPopulation, selectedIndividuals, context);
        return new NewBornPopulation(newBornPopulation);
    }

    public List<Integer> getBestLineage() {
        return bestLineage;
    }

    public EvaluatedIndividual getCurrentBest() {
        return currentBest;
    }

    public List<EvaluatedPopulation> getLineage() {
        return lineage;
    }
}
