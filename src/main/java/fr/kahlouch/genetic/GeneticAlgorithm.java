package fr.kahlouch.genetic;

import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.population.Population;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class GeneticAlgorithm {
    private GeneticAlgorithmParams params;
    private Double fitnessCap;
    private Long timeCapInMillis;
    private Individual previousBest;

    public GeneticAlgorithm(GeneticAlgorithmParams params, Long timeCapInMillis, Double fitnessCap) {
        if (params == null) throw new NullPointerException("params cannot be null");
        if (timeCapInMillis == null) throw new NullPointerException("timeCapInMillis cannot be null");
        this.params = params;
        this.fitnessCap = fitnessCap;
        this.timeCapInMillis = timeCapInMillis;
    }

    public GeneticAlgorithm(GeneticAlgorithmParams params, Long timeCapInMillis) {
        this(params, timeCapInMillis, null);
    }

    public Population compute(Population population) {
        long time = System.currentTimeMillis();
        Population currentPopulation = population;
        while ((fitnessCap == null || computeFitness(currentPopulation) < fitnessCap) && System.currentTimeMillis() - time < timeCapInMillis) {
            currentPopulation = generateNextGeneration(currentPopulation);
        }
        return population;
    }

    private double computeFitness(Population population) {
        final Individual best = population.getIndividuals().stream()
                .peek(Individual::computeFitness)
                .max(Comparator.comparingDouble(Individual::getFitness))
                .orElseThrow(RuntimeException::new);
        if (previousBest == null || previousBest.getFitness() < best.getFitness()) {
            this.previousBest = best;
        }
        return best.getFitness();
    }

    private Population generateNextGeneration(Population previousPopulation) {
        final List<Individual> selectedIndividuals = this.params.selection.select(previousPopulation.getIndividuals(), params);
        final List<Individual[]> individuals2Mate = this.params.pairing.pair(selectedIndividuals, params);
        final List<Individual> newPopulation = individuals2Mate.stream().flatMap(parents ->
                Arrays.stream(this.params.mating.mate(parents, params)).map(params.individualFactory::create)
        ).collect(Collectors.toList());
        this.params.filling.fill(newPopulation, selectedIndividuals, params);
        return new Population(newPopulation);
    }
}
