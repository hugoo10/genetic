package fr.kahlouch.genetic.genetic;

import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.filling.Filling;
import fr.kahlouch.genetic.mating.Mating;
import fr.kahlouch.genetic.mutation.Mutation;
import fr.kahlouch.genetic.pairing.Pairing;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;
import fr.kahlouch.genetic.selection.Selection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GeneticAlgorithm<C extends Chromosome<G>, G extends Gene> {
    private final Selection<C, G> selection;
    private final Pairing<C> pairing;
    private final Mating<C, G> mating;
    private final Mutation<G> mutation;
    private final Filling<C, G> filling;
    AbstractChromosomeFactory<C, G> chromosomeFactory;
    private C previousBest = null;

    private final List<Generation<C>> lineage;
    private final List<Integer> bestLineage = new ArrayList<>();

    public GeneticAlgorithm(Selection<C, G> selection, Pairing<C> pairing, Mating<C, G> mating, Mutation<G> mutation, Filling<C, G> filling, AbstractChromosomeFactory<C, G> chromosomeFactory) {
        this.selection = selection;
        this.pairing = pairing;
        this.mating = mating;
        this.mutation = mutation;
        this.filling = filling;
        this.lineage = new ArrayList<>();
        this.chromosomeFactory = chromosomeFactory;
    }

    private double computeFitness(Generation<C> generation) {
        this.lineage.add(generation);
        final C best = generation.getChromosomes().stream()
                .peek(Chromosome::computeFitness)
                .max(Comparator.comparingDouble(Chromosome::getFitness))
                .orElseThrow(RuntimeException::new);
        if (previousBest == null || previousBest.getFitness() < best.getFitness()) {
            this.bestLineage.add(lineage.size() - 1);
            System.err.println(lineage.size() + " :: " + best.toString());
        }
        this.previousBest = best;
        return best.getFitness();
    }

    private Generation<C> generateNextGeneration(Generation<C> previousGeneration) {
        final List<C> nextGeneration = this.selection.select(previousGeneration);
        final List<List<C>> readyToMate = this.pairing.pair(previousGeneration);
        readyToMate.forEach(parents ->
                nextGeneration.addAll(this.mating.mate(parents.get(0), parents.get(1)))
        );
        this.filling.fill(nextGeneration);
        return new Generation<>(nextGeneration);
    }

    public Generation<C> computeUntilFitnessAbove(Generation<C> generation, double fitnessCap, Long timeCap) {
        long time = System.currentTimeMillis();
        this.lineage.clear();
        Generation<C> currentGeneration = generation;
        while ((timeCap == null || System.currentTimeMillis() - time < timeCap) && computeFitness(currentGeneration) < fitnessCap) {
            currentGeneration = generateNextGeneration(currentGeneration);
        }
        return currentGeneration;
    }

    public List<Generation<C>> getLineage() {
        return lineage;
    }

    public C getPreviousBest() {
        return previousBest;
    }

    public Selection<C, G> getSelection() {
        return selection;
    }

    public Pairing<C> getPairing() {
        return pairing;
    }

    public Mating<C, G> getMating() {
        return mating;
    }

    public Mutation<G> getMutation() {
        return mutation;
    }

    public Filling<C, G> getFilling() {
        return filling;
    }

    public AbstractChromosomeFactory<C, G> getChromosomeFactory() {
        return chromosomeFactory;
    }


    public List<Integer> getBestLineage() {
        return bestLineage;
    }


}
