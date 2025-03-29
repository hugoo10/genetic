package fr.kahlouch.genetic.algorithm.execution.context;

import fr.kahlouch.genetic.algorithm.execution.context.builder.ExecutionContextBuilderStep;
import fr.kahlouch.genetic.algorithm.execution.context.step.crossover.Crossover;
import fr.kahlouch.genetic.algorithm.execution.context.step.elitism.Elitism;
import fr.kahlouch.genetic.algorithm.execution.context.step.filling.Filling;
import fr.kahlouch.genetic.algorithm.execution.context.step.filling.FillingContext;
import fr.kahlouch.genetic.algorithm.execution.context.step.mutation.Mutation;
import fr.kahlouch.genetic.algorithm.execution.context.step.repair.Repair;
import fr.kahlouch.genetic.algorithm.execution.context.step.selection.Selection;
import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public final class ExecutionContext<G extends Gene, I extends Individual<G, T>, T> implements Function<List<I>, List<I>> {
    private final Elitism<G, I, T> elitism;
    private final Selection<G, I, T> selection;
    private final Crossover<G, I, T> crossover;
    private final Mutation<G> mutation;
    private final Filling<G, I, T> filling;
    private final Repair<G> repair;
    private final ExecutionHelper<G, I, T> executionHelper;

    private ExecutionContext(Elitism<G, I, T> elitism, Selection<G, I, T> selection, Crossover<G, I, T> crossover, Mutation<G> mutation, Filling<G, I, T> filling, Repair<G> repair, ExecutionHelper<G, I, T> executionHelper, int minPopulationSize, int maxPopulationSize) {
        Objects.requireNonNull(elitism);
        Objects.requireNonNull(selection);
        Objects.requireNonNull(crossover);
        Objects.requireNonNull(mutation);

        this.elitism = Objects.requireNonNull(elitism);
        this.selection = Objects.requireNonNull(selection);
        this.crossover = Objects.requireNonNull(crossover);
        this.mutation = Objects.requireNonNull(mutation);
        this.filling = Objects.requireNonNull(filling);
        this.repair = Objects.requireNonNull(repair);
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

        final var additionalFilled = filling.apply(new FillingContext<>(previousIndividuals, children.size() + elites.size()))
                .stream()
                .map(mutation::apply)
                .map(repair)
                .map(executionHelper::createIndividual)
                .toList();


        final var nextIndividuals = new ArrayList<>(elites);
        nextIndividuals.addAll(children);
        nextIndividuals.addAll(additionalFilled);

        return nextIndividuals;
    }

    public static <G extends Gene, I extends Individual<G, T>, T> ExecutionContextBuilderStep<G, I, T> builder() {
        return helper ->
                individualSize ->
                        (minSize, maxSize) ->
                                (elitismType, elitismPercent) ->
                                        (selectionType, selectionPercent) ->
                                                crossoverType ->
                                                        (mutationType, mutationOdd) ->
                                                                (fillingType, topIndividualsToBreedPercent) ->
                                                                        repair -> {
                                                                            final var elitism = Elitism.<G, I, T>of(elitismType, elitismPercent);
                                                                            final var selection = Selection.<G, I, T>of(selectionType, selectionPercent);
                                                                            final var crossover = Crossover.of(crossoverType, helper);
                                                                            final var mutation = Mutation.of(mutationType, mutationOdd, helper);
                                                                            final var filling = Filling.of(fillingType, individualSize, minSize, helper, topIndividualsToBreedPercent, crossover);

                                                                            return new ExecutionContext<>(elitism, selection, crossover, mutation, filling, repair, helper, minSize, maxSize);
                                                                        };
    }
}
