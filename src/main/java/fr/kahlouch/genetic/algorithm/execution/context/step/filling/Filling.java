package fr.kahlouch.genetic.algorithm.execution.context.step.filling;

import fr.kahlouch.genetic.algorithm.execution.context.step.ExecutionStep;
import fr.kahlouch.genetic.algorithm.execution.context.step.crossover.Crossover;
import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;

public abstract class Filling<G extends Gene, I extends Individual<G, T>, T> extends ExecutionStep<FillingContext<G, I, T>, List<List<G>>> {
    protected final int individualSize;
    protected final int minPopulationSize;
    protected final ExecutionHelper<G, I, T> executionHelper;

    protected Filling(int individualSize, int minPopulationSize, ExecutionHelper<G, I, T> executionHelper) {
        this.individualSize = individualSize;
        this.minPopulationSize = minPopulationSize;
        this.executionHelper = executionHelper;
    }

    public static <G extends Gene, I extends Individual<G, T>, T> Filling<G, I, T> of(FillingType type, int individualSize, int minPopulationSize, ExecutionHelper<G, I, T> helper, double topIndivualsToBreedPercent, Crossover<G, I, T> crossover) {
        return switch (type) {
            case RANDOM -> new RandomFilling<>(individualSize, minPopulationSize, helper);
            case RANDOM_BREED_BEST ->
                    new RandomBreedBestFilling<>(individualSize, minPopulationSize, helper, topIndivualsToBreedPercent, crossover);
        };
    }
}
