package fr.kahlouch.genetic.algorithm;

import fr.kahlouch.genetic.algorithm.step.crossover.Crossover;
import fr.kahlouch.genetic.algorithm.step.elitism.Elitism;
import fr.kahlouch.genetic.algorithm.step.mutation.Mutation;
import fr.kahlouch.genetic.algorithm.step.repair.Repair;
import fr.kahlouch.genetic.algorithm.step.selection.Selection;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public record ExecutionGeneration<G extends Gene, I extends Individual<G, T>, T>(Elitism<G, I, T> elitism,
                                                                                 Selection<G, I, T> selection,
                                                                                 Crossover<G, I, T> crossover,
                                                                                 Mutation<G> mutation,
                                                                                 @Nullable
                                                                                 Repair<G> repair,
                                                                                 ExecutionGenerationHelper<G, I, T> executionGenerationHelper) implements Function<List<I>, List<I>> {

    public ExecutionGeneration {
        Objects.requireNonNull(elitism);
        Objects.requireNonNull(selection);
        Objects.requireNonNull(crossover);
        Objects.requireNonNull(mutation);
        repair = Objects.requireNonNullElseGet(repair, () -> lg -> lg);
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
                .map(executionGenerationHelper::createIndividual)
                .toList();

        final var nextIndividuals = new ArrayList<>(elites);
        nextIndividuals.addAll(children);

        return nextIndividuals;
    }
}
