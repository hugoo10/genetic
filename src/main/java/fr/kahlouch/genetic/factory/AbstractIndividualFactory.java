package fr.kahlouch.genetic.factory;

import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Individual;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
public abstract class AbstractIndividualFactory {
    private final int chromosomeSize;
    private final AbstractGeneFactory geneFactory;

    public AbstractIndividualFactory(int chromosomeSize, AbstractGeneFactory geneFactory) {
        this.chromosomeSize = chromosomeSize;
        this.geneFactory = geneFactory;
    }

    public abstract Individual create(List<Gene> chromosome);

    public final Individual createRandom() {
        final List<Gene> chromosome = new ArrayList<>();
        for (int i = 0; i < chromosomeSize; ++i) {
            chromosome.add(this.geneFactory.createRandom());
        }
        return this.create(chromosome);
    }
}
