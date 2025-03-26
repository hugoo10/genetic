package fr.kahlouch.genetic.algorithm.execution.context;

import fr.kahlouch.genetic.algorithm.execution.context.builder.AlgorithmHelperBuilderStep;
import fr.kahlouch.genetic.algorithm.execution.context.step.crossover.Crossover;
import fr.kahlouch.genetic.algorithm.execution.context.step.elitism.Elitism;
import fr.kahlouch.genetic.algorithm.execution.context.step.mutation.Mutation;
import fr.kahlouch.genetic.algorithm.execution.context.step.repair.Repair;
import fr.kahlouch.genetic.algorithm.execution.context.step.selection.Selection;
import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class ExecutionContext<G extends Gene, I extends Individual<G, T>, T> implements Function<List<I>, List<I>> {
    private final Elitism<G, I, T> elitism;
    private final Selection<G, I, T> selection;
    private final Crossover<G, I, T> crossover;
    private final Mutation<G> mutation;
    @Nullable
    private final Repair<G> repair;
    private final ExecutionHelper<G, I, T> executionHelper;

    private ExecutionContext(Elitism<G, I, T> elitism, Selection<G, I, T> selection, Crossover<G, I, T> crossover, Mutation<G> mutation, @Nullable Repair<G> repair, ExecutionHelper<G, I, T> executionHelper) {
        Objects.requireNonNull(elitism);
        Objects.requireNonNull(selection);
        Objects.requireNonNull(crossover);
        Objects.requireNonNull(mutation);

        this.elitism = Objects.requireNonNull(elitism);
        this.selection = Objects.requireNonNull(selection);
        this.crossover = Objects.requireNonNull(crossover);
        this.mutation = Objects.requireNonNull(mutation);
        this.repair = Objects.requireNonNullElseGet(repair, () -> lg -> lg);
        this.executionHelper = executionHelper;
    }

    @Override
    public List<I> apply(List<I> previousIndividuals) {
        Objects.requireNonNull(repair);

        final var elites = elitism.apply(previousIndividuals);

        final var children = selection.apply(previousIndividuals).parallelStream().unordered()
                .map(crossover)
                .flatMap(List::stream)
                .map(mutation::apply)
                .map(repair)
                .map(executionHelper::createIndividual)
                .toList();

        final var nextIndividuals = new ArrayList<>(elites);
        nextIndividuals.addAll(children);

        return nextIndividuals;
    }

    public static <G extends Gene, I extends Individual<G, T>, T> AlgorithmHelperBuilderStep<G, I, T> builder() {
        return helper ->
                populationSize ->
                        (elitismType, elitismPercent) ->
                                (selectionType, selectionPercent) ->
                                        crossoverType ->
                                                (mutationType, mutationOdd) ->
                                                        repair -> {
                                                            final var elitism = Elitism.<G, I, T>of(elitismType, elitismPercent);
                                                            final var selection = Selection.<G, I, T>of(selectionType, selectionPercent);
                                                            final var crossover = Crossover.of(crossoverType, helper);
                                                            final var mutation = Mutation.of(mutationType, mutationOdd, helper);

                                                            return new ExecutionContext<>(elitism, selection, crossover, mutation, repair, helper);
                                                        };
    }
}
