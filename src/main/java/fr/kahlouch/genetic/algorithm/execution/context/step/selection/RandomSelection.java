package fr.kahlouch.genetic.algorithm.execution.context.step.selection;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.ArrayList;
import java.util.List;

public class RandomSelection<G extends Gene, I extends Individual<G, T>, T> implements Selection<G, I, T> {
    private final double percent;

    public RandomSelection(double percent) {
        this.percent = percent;
    }

    @Override
    public List<Parents<G, I, T>> apply(List<I> is) {
        List<Individual[]> pairs = new ArrayList<>();
        Individual[] pair;
        while (pairs.size() < params.pairingSize / 2) {
            pair = new Individual[2];
            pair[0] = individuals.get(Constants.RANDOM_GEN.nextInt(individuals.size()));
            pair[1] = individuals.get(Constants.RANDOM_GEN.nextInt(individuals.size()));
            pairs.add(pair);
        }
        return pairs;
    }
}
