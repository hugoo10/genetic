package fr.kahlouch.genetic.algorithms.selection;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;

import java.util.List;

public abstract class Selection {
    protected abstract List<Individual> select(List<Individual> individuals, GeneticAlgorithmParams params);
}
