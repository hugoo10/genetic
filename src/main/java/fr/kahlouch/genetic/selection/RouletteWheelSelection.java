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
    public RouletteWheelSelection(AbstractChromosomeFactory<C, G> chromosomeFactory) {
        super(chromosomeFactory);
    }

    @Override
    protected List<C> doSelect(Generation<C> generation) {
        generation.getChromosomes().sort(Comparator.comparingDouble(Chromosome::getFitness));
        double[] cumulativeSum = new double[Constants.POPULATION_SIZE];
        double sum = 0;
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i) {
            cumulativeSum[i] = generation.getChromosomes().get(i).getFitness();
            sum += cumulativeSum[i];
        }
        for (int i = 0; i < Constants.POPULATION_SIZE; ++i) {
            if (i + 1 < Constants.POPULATION_SIZE) {
                cumulativeSum[i + 1] += cumulativeSum[i];
            }
            cumulativeSum[i] = cumulativeSum[i] / sum;
        }
        List<C> selected = new ArrayList<>();
        double random;
        while (selected.size() < Constants.SELECTION_SIZE) {
            random = Constants.RANDOM_GEN.nextDouble();
            int i = 0;
            while (i < Constants.POPULATION_SIZE && cumulativeSum[i] < random) {
                ++i;
            }
            if (i < Constants.POPULATION_SIZE) {
                selected.add(generation.getChromosomes().get(i));
            }
        }
        return selected;
    }
}
