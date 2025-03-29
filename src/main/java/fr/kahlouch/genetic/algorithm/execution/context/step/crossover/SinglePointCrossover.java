package fr.kahlouch.genetic.algorithm.execution.context.step.crossover;

import fr.kahlouch.genetic.algorithm.execution.context.step.selection.Parents;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;

public class SinglePointCrossover<G extends Gene, I extends Individual<G, T>, T> extends Crossover<G, I, T> {

    @Override
    public List<List<G>> apply(Parents<G, I, T> parents) {
        final var parentGenes1 = parents.individual1().getGenes();
        final var parentGenes2 = parents.individual2().getGenes();
        int splitIndex = random.nextInt(parentGenes1.size());

        final var genes1 = new ArrayList<G>();
        final var genes2 = new ArrayList<G>();

        genes1.addAll(parentGenes1.subList(0, splitIndex));
        genes1.addAll(parentGenes2.subList(splitIndex, parentGenes2.size()));

        genes2.addAll(parentGenes2.subList(0, splitIndex));
        genes2.addAll(parentGenes1.subList(splitIndex, parentGenes1.size()));

        final List<List<G>> breededGenes = new ArrayList<>();
        breededGenes.add(genes1);
        breededGenes.add(genes2);
        return breededGenes;
    }
}
