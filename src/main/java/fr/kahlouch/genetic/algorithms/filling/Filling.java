package fr.kahlouch.genetic.algorithms.filling;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.factory.AbstractIndividualFactory;
import fr.kahlouch.genetic.population.Individual;
import lombok.Setter;

import java.util.List;

@Setter
public abstract class Filling {
    protected AbstractIndividualFactory individualFactory;

    final void fill(List<Individual> population, List<Individual> selected, GeneticAlgorithmParams params) {
        while (population.size() < params.populationSize) {
            List<Individual> individuals = doFill(population, selected, params);
            int it = 0;
            while (population.size() < params.populationSize && it < individuals.size()) {
                population.add(individuals.get(it++));
            }
        }
    }

    abstract List<Individual> doFill(List<Individual> individuals, List<Individual> selected, GeneticAlgorithmParams params);
}
