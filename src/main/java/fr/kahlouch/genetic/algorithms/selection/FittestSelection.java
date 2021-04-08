package fr.kahlouch.genetic.algorithms.selection;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FittestSelection extends Selection {
    @Override
    protected List<Individual> select(List<Individual> individuals, GeneticAlgorithmParams params) {
        individuals.sort(Comparator.comparingDouble(Individual::getFitness));
        return new ArrayList<>(individuals.subList(individuals.size() - params.selectionSize, individuals.size()));
    }
}
