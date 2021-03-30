package fr.kahlouch.genetic.selection;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FittestSelection<C extends Chromosome<G>, G extends Gene> extends Selection<C, G> {
    public FittestSelection(AbstractChromosomeFactory<C, G> chromosomeFactory) {
        super(chromosomeFactory);
    }

    @Override
    protected List<C> doSelect(Generation<C> generation) {
        generation.getChromosomes().sort(Comparator.comparingDouble(Chromosome::getFitness));
        return new ArrayList<>(generation.getChromosomes().subList(Constants.POPULATION_SIZE - 10, Constants.POPULATION_SIZE));
    }
}
