package fr.kahlouch.genetic.algorithm.execution.context.step.filling;

import fr.kahlouch.genetic.algorithm.execution.context.step.crossover.Crossover;
import fr.kahlouch.genetic.algorithm.execution.context.step.selection.Parents;
import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class RandomBreedBestFilling<G extends Gene, I extends Individual<G, T>, T> extends Filling<G, I, T> {
    private final double topIndividualsToBreedPercent;
    private final Crossover<G, I, T> crossover;

    protected RandomBreedBestFilling(int individualSize, int minPopulationSize, ExecutionHelper<G, I, T> executionHelper,
                                     double topIndividualsToBreedPercent, Crossover<G, I, T> crossover) {
        super(individualSize, minPopulationSize, executionHelper);
        this.topIndividualsToBreedPercent = topIndividualsToBreedPercent;
        this.crossover = crossover;
    }

    @Override
    public List<List<G>> apply(FillingContext<G, I, T> fillingContext) {
        final var missingIndividuals = Math.max(0, minPopulationSize - fillingContext.newPopulationSize());

        final var nbSelected = Math.round(fillingContext.individuals().size() * this.topIndividualsToBreedPercent);

        final var toBreedIndividuals = fillingContext.individuals().stream()
                .sorted(Individual::compareTo)
                .sorted(Comparator.reverseOrder())
                .limit(nbSelected)
                .toList();


        return Stream.generate(() -> executionHelper.createRandomIndividual(individualSize))
                .map(randomIndividual -> {
                    final var toBreedIndividualIdx = random.nextInt(toBreedIndividuals.size());
                    final var toBreedIndividual = toBreedIndividuals.get(toBreedIndividualIdx);
                    return new Parents<>(toBreedIndividual, randomIndividual);
                })
                .map(crossover)
                .flatMap(List::stream)
                .limit(missingIndividuals)
                .toList();
    }
}
