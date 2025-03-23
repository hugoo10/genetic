package fr.kahlouch.genetic.algorithms._genetic;

import fr.kahlouch.genetic.algorithms.filling.Filling;
import fr.kahlouch.genetic.algorithms.mating.Mating;
import fr.kahlouch.genetic.algorithms.mutation.Mutation;
import fr.kahlouch.genetic.algorithms.pairing.Pairing;
import fr.kahlouch.genetic.algorithms.selection.Selection;
import fr.kahlouch.genetic.factory.GeneticFactory;

import java.util.Objects;

public record GeneticAlgorithmContext(int populationSize,
                                      GeneticFactory geneticFactory,
                                      Selection selection,
                                      Pairing pairing,
                                      Mating mating,
                                      Mutation mutation,
                                      Filling filling) {


    public GeneticAlgorithmContext {
        Objects.requireNonNull(geneticFactory);
        Objects.requireNonNull(selection);
        Objects.requireNonNull(pairing);
        Objects.requireNonNull(mating);
        Objects.requireNonNull(mutation);
        Objects.requireNonNull(filling);

        //TODO validate constraints
    }
}
