package fr.kahlouch.genetic;

import fr.kahlouch.genetic.algorithms.filling.FillingType;
import fr.kahlouch.genetic.algorithms.mating.MatingType;
import fr.kahlouch.genetic.algorithms.mutation.MutationType;
import fr.kahlouch.genetic.algorithms.pairing.PairingType;
import fr.kahlouch.genetic.algorithms.selection.SelectionType;
import fr.kahlouch.genetic.factory.AbstractGeneFactory;
import fr.kahlouch.genetic.factory.AbstractIndividualFactory;
import lombok.Builder;
import lombok.NonNull;


@Builder
public class GeneticAlgorithmParams {
    @NonNull
    @Builder.Default
    public final SelectionType selection = SelectionType.FITTEST;
    @NonNull
    @Builder.Default
    public final PairingType pairing = PairingType.FITTEST;
    @NonNull
    @Builder.Default
    public final MatingType mating = MatingType.TWO_POINTS;
    @NonNull
    @Builder.Default
    public final MutationType mutation = MutationType.RESET;
    @NonNull
    @Builder.Default
    public final FillingType filling = FillingType.RANDOM_BREED_BEST;

    @NonNull
    @Builder.Default
    public final AbstractGeneFactory geneFactory = null;
    @NonNull
    @Builder.Default
    public final AbstractIndividualFactory individualFactory = null;

    @NonNull
    @Builder.Default
    public final Double mutationOdd = 0.01;
    @NonNull
    @Builder.Default
    public final Integer populationSize = null;
    @NonNull
    @Builder.Default
    public final Integer selectionSize = null;
    @NonNull
    @Builder.Default
    public final Integer pairingSize = null;
    @NonNull
    @Builder.Default
    public final Integer fillingRetrieveTopSize = null;
}
