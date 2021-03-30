package fr.kahlouch.genetic.selection;

import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Selection<C extends Chromosome<G>, G extends Gene> {
    protected AbstractChromosomeFactory<C, G> chromosomeFactory;

    public Selection(AbstractChromosomeFactory<C, G> chromosomeFactory) {
        this.chromosomeFactory = chromosomeFactory;
    }

    public final List<C> select(Generation<C> generation) {
        return doSelect(generation)
                .parallelStream()
                .map(chromosome -> {
                    C copy = this.chromosomeFactory.create();
                    copy.setChromosomeType(Chromosome.ChromosomeType.SELECTED);
                    copy.addGenes(chromosome.getGenes());
                    return copy;
                }).collect(Collectors.toList());
    }

    protected abstract List<C> doSelect(Generation<C> generation);

}
