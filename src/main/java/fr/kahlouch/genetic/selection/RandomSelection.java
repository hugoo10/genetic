package fr.kahlouch.genetic.selection;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.List;

public class RandomSelection<C extends Chromosome<G>, G extends Gene> extends Selection<C, G> {

    protected List<C> doSelect(Generation<C> generation) {
        List<C> selected = new ArrayList<>();
        for (int i = 0; i < this.params.selectionSize; ++i) {
            selected.add(generation.getChromosomes().get(Constants.RANDOM_GEN.nextInt(this.params.populationSize)));
        }
        return selected;
    }
}
