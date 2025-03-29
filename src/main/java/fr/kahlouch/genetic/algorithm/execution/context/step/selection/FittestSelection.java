package fr.kahlouch.genetic.algorithm.execution.context.step.selection;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Gatherers;

public class FittestSelection<G extends Gene, I extends Individual<G, T>, T> extends Selection<G, I, T> {
    private final double percent;

    public FittestSelection(double percent) {
        this.percent = percent;
    }

    @Override
    public List<Parents<G, I, T>> apply(List<I> individuals) {
        final var selectionSize = Math.round(individuals.size() * percent);

        return individuals.stream()
                .sorted(Individual::compareTo)
                .sorted(Comparator.reverseOrder())
                .gather(Gatherers.windowFixed(2))
                .filter(list -> list.size() > 1)
                .map(list -> new Parents<>(list.getFirst(), list.getLast()))
                .limit(selectionSize)
                .toList();
    }
}
