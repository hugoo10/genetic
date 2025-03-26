package fr.kahlouch.genetic.algorithm.execution.context.step.selection;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FittestSelection<G extends Gene, I extends Individual<G, T>, T> implements Selection<G, I, T> {
    private final double percent;

    public FittestSelection(double percent) {
        this.percent = percent;
    }

    @Override
    public List<Parents<G, I, T>> apply(List<I> is) {
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
        List<I[]> pairs = new ArrayList<>();
        I[] pair;
        int chromosomeIndex = individuals.size() - 1;
        while (pairs.size() < params.pairingSize / 2) {
            pair = new Individual[2];
            pair[0] = individuals.get(chromosomeIndex--);
            pair[1] = individuals.get(chromosomeIndex--);
            pairs.add(pair);
        }
        return pairs;
    }
}
