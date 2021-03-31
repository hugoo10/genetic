package fr.kahlouch.genetic.selection;

import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FittestSelection<C extends Chromosome<G>, G extends Gene> extends Selection<C, G> {
    @Override
    protected List<C> doSelect(Generation<C> generation) {
        generation.getChromosomes().sort(Comparator.comparingDouble(Chromosome::getFitness));
        return new ArrayList<>(generation.getChromosomes().subList(this.params.populationSize - 10, this.params.populationSize));
    }
}
