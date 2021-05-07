package fr.kahlouch.genetic;

import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.population.Population;
import fr.kahlouch.genetic.utils.HistoryType;
import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class GeneticAlgorithm {
    private GeneticAlgorithmParams params;
    private Double fitnessCap;
    private Long timeCapInMillis;

    @Getter
    private List<Integer> bestLineage;
    @Getter
    private List<Population> lineage;
    @Getter
    private Individual previousBest;

    public GeneticAlgorithm(GeneticAlgorithmParams params, Double fitnessCap, Long timeCapInMillis) {
        if (params == null) throw new NullPointerException("params cannot be null");
        if (fitnessCap == null) throw new NullPointerException("fitnessCap cannot be null");
        this.params = params;
        this.fitnessCap = fitnessCap;
        this.timeCapInMillis = timeCapInMillis;
        this.lineage = new ArrayList<>();
        this.bestLineage = new ArrayList<>();
    }

    public GeneticAlgorithm(GeneticAlgorithmParams params, Double fitnessCap) {
        this(params, fitnessCap, null);
    }

    public Population compute(Population population, @NonNull HistoryType historyType) {
        long time = System.currentTimeMillis();
        Population currentPopulation = population;
        boolean haveTime;
        double lastComputedFitness;

        var generation = 0;
        do {
            lastComputedFitness = computeFitness(currentPopulation, generation, historyType);
            haveTime = timeCapInMillis == null || System.currentTimeMillis() - time < timeCapInMillis;
            if (haveTime) {
                currentPopulation = generateNextGeneration(currentPopulation);
            }
            ++generation;
        } while (haveTime && lastComputedFitness < fitnessCap);
        return currentPopulation;
    }


    private double computeFitness(Population population, int generation, HistoryType historyType) {
        final Individual best = population.getIndividuals().stream()
                .peek(Individual::computeFitness)
                .max(Comparator.comparingDouble(Individual::getFitness))
                .orElseThrow(RuntimeException::new);
        if (historyType == HistoryType.FULL) {
            this.lineage.add(population);
        }
        final var newBest = previousBest == null || previousBest.getFitness() < best.getFitness();
        if (newBest) {
            this.previousBest = best;
        }
        if ((newBest && historyType == HistoryType.ONLY_BEST) || historyType == HistoryType.FULL) {
            this.lineage.add(population);
            if (newBest) {
                this.bestLineage.add(generation);
                System.err.println((generation + 1) + " :: " + best.toString());
            }
        }
        return best.getFitness();
    }

    private Population generateNextGeneration(Population previousPopulation) {
        final List<Individual> selectedIndividuals = this.params.selection.select(previousPopulation.getIndividuals(), params);
        final List<Individual[]> individuals2Mate = this.params.pairing.pair(previousPopulation.getIndividuals(), params);
        final List<Individual> newPopulation = individuals2Mate.stream().flatMap(parents ->
                Arrays.stream(this.params.mating.mate(parents, params)).map(params.individualFactory::create)
        ).collect(Collectors.toList());
        newPopulation.addAll(selectedIndividuals);
        this.params.filling.fill(newPopulation, selectedIndividuals, params);
        return new Population(newPopulation);
    }
}
