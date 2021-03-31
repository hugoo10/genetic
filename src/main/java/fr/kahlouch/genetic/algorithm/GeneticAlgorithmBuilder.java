package fr.kahlouch.genetic.algorithm;

import fr.kahlouch.genetic.algorithm.exception.GeneticAlgorithmBuilderException;
import fr.kahlouch.genetic.algorithm.param.GeneticAlgorithmParam;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.factory.AbstractGeneFactory;
import fr.kahlouch.genetic.filling.Filling;
import fr.kahlouch.genetic.mating.Mating;
import fr.kahlouch.genetic.mutation.Mutation;
import fr.kahlouch.genetic.pairing.Pairing;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.selection.Selection;

public final class GeneticAlgorithmBuilder<C extends Chromosome<G>, G extends Gene> {
    private Selection<C, G> selection;
    private Pairing<C> pairing;
    private Mating<C, G> mating;
    private Mutation<G> mutation;
    private Filling<C, G> filling;
    private AbstractChromosomeFactory<C, G> chromosomeFactory;
    private AbstractGeneFactory<G> geneFactory;
    private GeneticAlgorithmParam geneticAlgorithmParam;

    public GeneticAlgorithmBuilder<C, G> selection(Selection<C, G> selection) {
        this.selection = selection;
        return this;
    }

    public GeneticAlgorithmBuilder<C, G> pairing(Pairing<C> pairing) {
        this.pairing = pairing;
        return this;
    }

    public GeneticAlgorithmBuilder<C, G> mating(Mating<C, G> mating) {
        this.mating = mating;
        return this;
    }

    public GeneticAlgorithmBuilder<C, G> mutation(Mutation<G> mutation) {
        this.mutation = mutation;
        return this;
    }

    public GeneticAlgorithmBuilder<C, G> filling(Filling<C, G> filling) {
        this.filling = filling;
        return this;
    }

    public GeneticAlgorithmBuilder<C, G> chromosomeFactory(AbstractChromosomeFactory<C, G> chromosomeFactory) {
        this.chromosomeFactory = chromosomeFactory;
        return this;
    }

    public GeneticAlgorithmBuilder<C, G> geneFactory(AbstractGeneFactory<G> geneFactory) {
        this.geneFactory = geneFactory;
        return this;
    }

    public GeneticAlgorithmBuilder<C, G> geneticAlgorithmParam(GeneticAlgorithmParam geneticAlgorithmParam) {
        this.geneticAlgorithmParam = geneticAlgorithmParam;
        return this;
    }

    private void validate() {
        if (this.selection == null) throw new GeneticAlgorithmBuilderException(Selection.class);
        if (this.pairing == null) throw new GeneticAlgorithmBuilderException(Pairing.class);
        if (this.mating == null) throw new GeneticAlgorithmBuilderException(Mating.class);
        if (this.mutation == null) throw new GeneticAlgorithmBuilderException(Mutation.class);
        if (this.filling == null) throw new GeneticAlgorithmBuilderException(Filling.class);
        if (this.chromosomeFactory == null) throw new GeneticAlgorithmBuilderException(AbstractChromosomeFactory.class);
        if (this.geneFactory == null) throw new GeneticAlgorithmBuilderException(AbstractGeneFactory.class);
        if (this.geneticAlgorithmParam == null) throw new GeneticAlgorithmBuilderException(GeneticAlgorithmParam.class);
    }

    public GeneticAlgorithm<C, G> build() {
        validate();

        this.chromosomeFactory.setGeneFactory(this.geneFactory);
        this.chromosomeFactory.setParams(this.geneticAlgorithmParam);

        this.selection.setChromosomeFactory(this.chromosomeFactory);
        this.selection.setParams(this.geneticAlgorithmParam);

        this.pairing.setParams(this.geneticAlgorithmParam);

        this.mutation.setAbstractGeneFactory(this.geneFactory);
        this.mutation.setParams(this.geneticAlgorithmParam);

        this.mating.setMutation(this.mutation);
        this.mating.setChromosomeFactory(this.chromosomeFactory);
        this.mating.setParams(this.geneticAlgorithmParam);

        this.filling.setMating(this.mating);
        this.filling.setChromosomeFactory(this.chromosomeFactory);
        this.filling.setParams(this.geneticAlgorithmParam);

        return new GeneticAlgorithm<>(selection, pairing, mating, mutation, filling, chromosomeFactory);
    }


}
