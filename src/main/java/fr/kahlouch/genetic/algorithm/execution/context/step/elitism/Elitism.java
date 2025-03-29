package fr.kahlouch.genetic.algorithm.execution.context.step.elitism;

import fr.kahlouch.genetic.algorithm.execution.context.step.ExecutionStep;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;

public abstract class Elitism<G extends Gene, I extends Individual<G, T>, T> extends ExecutionStep<List<I>, List<I>> {
    protected final double percent;

    protected Elitism(double percent) {
        this.percent = percent;
    }

    public static <G extends Gene, I extends Individual<G, T>, T> Elitism<G, I, T> of(ElitismType type, double percent) {
        return switch (type) {
            case FITTEST -> new FittestElitism<>(percent);
            case RANDOM -> new RandomElitism<>(percent);
            case ROULETTE_WHEEL -> new RouletteWheelElitism<>(percent);
        };
    }
}
