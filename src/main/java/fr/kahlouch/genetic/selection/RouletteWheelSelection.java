package fr.kahlouch.genetic.selection;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RouletteWheelSelection<C extends Chromosome<G>, G extends Gene> extends Selection<C, G> {

    @Override
    protected List<C> doSelect(Generation<C> generation) {
        generation.getChromosomes().sort(Comparator.comparingDouble(Chromosome::getFitness));
        double[] cumulativeSum = new double[this.params.populationSize];
        double sum = 0;
        for (int i = 0; i < this.params.populationSize; ++i) {
            cumulativeSum[i] = generation.getChromosomes().get(i).getFitness();
            sum += cumulativeSum[i];
        }
        for (int i = 0; i < this.params.populationSize; ++i) {
            if (i + 1 < this.params.populationSize) {
                cumulativeSum[i + 1] += cumulativeSum[i];
            }
            cumulativeSum[i] = cumulativeSum[i] / sum;
        }
        List<C> selected = new ArrayList<>();
        double random;
        while (selected.size() < this.params.selectionSize) {
            random = Constants.RANDOM_GEN.nextDouble();
            int i = 0;
            while (i < this.params.populationSize && cumulativeSum[i] < random) {
                ++i;
            }
            if (i < this.params.populationSize) {
                selected.add(generation.getChromosomes().get(i));
            }
        }
        return selected;
    }
}
