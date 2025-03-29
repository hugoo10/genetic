package fr.kahlouch.genetic.algorithm.execution.context.step.filling;

import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;
import java.util.stream.Stream;

public class RandomFilling<G extends Gene, I extends Individual<G, T>, T> extends Filling<G, I, T> {
    protected RandomFilling(int individualSize, int minPopulationSize, ExecutionHelper<G, I, T> executionHelper) {
        super(individualSize, minPopulationSize, executionHelper);
    }

    @Override
    public List<List<G>> apply(FillingContext<G, I, T> fillingContext) {
        final var missingIndividuals = Math.max(0, minPopulationSize - fillingContext.newPopulationSize());

        return Stream.generate(() -> executionHelper.createRandomIndividual(individualSize))
                .map(Individual::getGenes)
                .limit(missingIndividuals)
                .toList();
    }
}
