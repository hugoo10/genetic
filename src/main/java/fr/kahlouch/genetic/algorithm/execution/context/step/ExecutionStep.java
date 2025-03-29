package fr.kahlouch.genetic.algorithm.execution.context.step;

import java.util.Random;
import java.util.function.Function;

public abstract class ExecutionStep<I, O> implements Function<I, O> {
    protected final Random random = new Random(System.nanoTime());
}
