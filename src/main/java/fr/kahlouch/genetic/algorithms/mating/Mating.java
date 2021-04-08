package fr.kahlouch.genetic.algorithms.mating;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Individual;

import java.util.List;

public abstract class Mating {
    final List<Gene>[] mate(Individual[] parents, GeneticAlgorithmParams params) {
        return doMate(parents[0].getChromosome(), parents[1].getChromosome(), params);
    }

    protected abstract List<Gene>[] doMate(List<Gene> parentChromosome1, List<Gene> parentChromosome2, GeneticAlgorithmParams params);
}
