package fr.kahlouch.genetic.selection;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.List;

public class RandomSelection<C extends Chromosome<G>, G extends Gene> extends Selection<C, G> {
    public RandomSelection(AbstractChromosomeFactory<C, G> chromosomeFactory) {
        super(chromosomeFactory);
    }

    protected List<C> doSelect(Generation<C> generation) {
        List<C> selected = new ArrayList<>();
        for (int i = 0; i < Constants.SELECTION_SIZE; ++i) {
            selected.add(generation.getChromosomes().get(Constants.RANDOM_GEN.nextInt(Constants.POPULATION_SIZE)));
        }
        return selected;
    }
}
