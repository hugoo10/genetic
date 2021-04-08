package fr.kahlouch.genetic.algorithms.pairing;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;

import java.util.List;

public abstract class Pairing {
    protected abstract List<Individual[]> pair(List<Individual> individuals, GeneticAlgorithmParams params);
}
