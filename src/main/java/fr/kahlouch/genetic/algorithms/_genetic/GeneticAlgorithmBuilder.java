package fr.kahlouch.genetic.algorithms._genetic;

import fr.kahlouch.genetic.algorithms.filling.Filling;
import fr.kahlouch.genetic.algorithms.mating.Mating;
import fr.kahlouch.genetic.algorithms.mutation.Mutation;
import fr.kahlouch.genetic.algorithms.pairing.Pairing;
import fr.kahlouch.genetic.algorithms.selection.Selection;
import fr.kahlouch.genetic.factory.GeneticFactory;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public class GeneticAlgorithmBuilder {
    private final GeneticFactory geneticFactory;
    private final int populationSize;

    @Nullable
    private Selection selection;
    @Nullable
    private Pairing pairing;
    @Nullable
    private Mating mating;
    @Nullable
    private Mutation mutation;
    @Nullable
    private Filling filling;

    GeneticAlgorithmBuilder(int populationSize, GeneticFactory geneticFactory) {
        Objects.requireNonNull(geneticFactory);
        this.populationSize = populationSize;
        this.geneticFactory = geneticFactory;
    }

    public GeneticAlgorithm build() {
        final var selection = Objects.requireNonNullElseGet(this.selection, Selection::of);
        final var pairing = Objects.requireNonNullElseGet(this.pairing, Pairing::of);
        final var mating = Objects.requireNonNullElseGet(this.mating, Mating::of);
        final var mutation = Objects.requireNonNullElseGet(this.mutation, Mutation::of);
        final var filling = Objects.requireNonNullElseGet(this.filling, Filling::of);

        return new GeneticAlgorithm(this.populationSize, this.geneticFactory, selection, pairing, mating, mutation, filling);
    }
}
