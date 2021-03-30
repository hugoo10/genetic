package fr.kahlouch.genetic.mating;

import fr.kahlouch.genetic.algorithm.param.GeneticAlgorithmParam;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.mutation.Mutation;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

import java.util.ArrayList;
import java.util.List;

public abstract class Mating<C extends Chromosome<G>, G extends Gene> {
    protected AbstractChromosomeFactory<C, G> chromosomeFactory;
    protected Mutation<G> mutation;
    protected GeneticAlgorithmParam params;

    public void setChromosomeFactory(AbstractChromosomeFactory<C, G> chromosomeFactory) {
        this.chromosomeFactory = chromosomeFactory;
    }

    public void setMutation(Mutation<G> mutation) {
        this.mutation = mutation;
    }

    public void setParams(GeneticAlgorithmParam params) {
        this.params = params;
    }

    public final List<C> mate(C parent1, C parent2) {
        final List<C> childs = new ArrayList<>();
        final C child1 = this.chromosomeFactory.create();
        final C child2 = this.chromosomeFactory.create();
        child1.setChromosomeType(Chromosome.ChromosomeType.CHILD);
        child2.setChromosomeType(Chromosome.ChromosomeType.CHILD);
        childs.add(child1);
        childs.add(child2);
        return doMate(parent1, parent2, childs);
    }


    public abstract List<C> doMate(C parent1, C parent2, List<C> childs);
}
