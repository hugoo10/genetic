package fr.kahlouch.genetic.algorithm.execution.context.step.selection;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class RandomSelection<G extends Gene, I extends Individual<G, T>, T> extends Selection<G, I, T> {
    private final double percent;

    public RandomSelection(double percent) {
        this.percent = percent;
    }

    @Override
    public List<Parents<G, I, T>> apply(List<I> individuals) {
        final var selectionSize = Math.round(individuals.size() * percent);

        record PairingIndex(int idx1, int idx2) {
        }

        return Stream.generate(() -> new PairingIndex(
                        random.nextInt(individuals.size()),
                        random.nextInt(individuals.size())))
                .map(pairingIndex -> {
                    final var parent1 = individuals.get(pairingIndex.idx1());
                    final var parent2 = individuals.get(pairingIndex.idx2());
                    return new Parents<>(parent1, parent2);
                })
                .limit(selectionSize)
                .toList();
    }
}
