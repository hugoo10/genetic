package fr.kahlouch.genetic.algorithm.execution.context.step.crossover;

import fr.kahlouch.genetic.algorithm.execution.context.step.selection.Parents;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TwoPointsCrossover<G extends Gene, I extends Individual<G, T>, T> implements Crossover<G, I, T> {
    private final Random random = new Random(System.currentTimeMillis());

    @Override
    public List<List<G>> apply(Parents<G, I, T> parents) {
        final var parentGenes1 = parents.individual1().getGenes();
        final var parentGenes2 = parents.individual2().getGenes();

        int splitIndex1 = random.nextInt(parentGenes1.size());
        int splitIndex2 = random.nextInt(parentGenes1.size() - splitIndex1) + splitIndex1;

        final var genes1 = new ArrayList<G>();
        final var genes2 = new ArrayList<G>();

        genes1.addAll(parentGenes1.subList(0, splitIndex1));
        genes1.addAll(parentGenes2.subList(splitIndex1, splitIndex2));
        genes1.addAll(parentGenes1.subList(splitIndex2, parentGenes1.size()));

        genes2.addAll(parentGenes2.subList(0, splitIndex1));
        genes2.addAll(parentGenes1.subList(splitIndex1, splitIndex2));
        genes2.addAll(parentGenes2.subList(splitIndex2, parentGenes2.size()));

        final List<List<G>> breededGenes = new ArrayList<>();
        breededGenes.add(genes1);
        breededGenes.add(genes2);
        return breededGenes;
    }
}
