package fr.kahlouch.genetic.algorithm.execution.context.step.crossover;

import fr.kahlouch.genetic.algorithm.helper.ExecutionHelper;
import fr.kahlouch.genetic.algorithm.execution.context.step.selection.Parents;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContinuousCrossover<G extends Gene, I extends Individual<G, T>, T> implements Crossover<G, I, T> {
    private final Random random = new Random(System.currentTimeMillis());
    private final ExecutionHelper<G, I, T> executionHelper;

    public ContinuousCrossover(ExecutionHelper<G, I, T> executionHelper) {
        this.executionHelper = executionHelper;
    }

    @Override
    public List<List<G>> apply(Parents<G, I, T> parents) {
        final double randomPercent = random.nextDouble();
        final var parentGenes1 = parents.individual1().getGenes();
        final var parentGenes2 = parents.individual2().getGenes();

        final var genes1 = new ArrayList<G>();
        final var genes2 = new ArrayList<G>();

        for (int i = 0; i < parentGenes1.size(); ++i) {
            final var gene1 = parentGenes1.get(i);
            final var gene2 = parentGenes2.get(i);

            genes1.add(executionHelper.breedGenes(gene1, gene2, randomPercent));
            genes2.add(executionHelper.breedGenes(gene2, gene1, randomPercent));
        }

        final List<List<G>> breededGenes = new ArrayList<>();
        breededGenes.add(genes1);
        breededGenes.add(genes2);
        return breededGenes;
    }
}
