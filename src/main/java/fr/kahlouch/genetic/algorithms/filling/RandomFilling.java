package fr.kahlouch.genetic.algorithms.filling;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;

import java.util.Collections;
import java.util.List;

public class RandomFilling extends Filling {

    @Override
    List<Individual> doFill(List<Individual> population, List<Individual> selected, GeneticAlgorithmParams params) {
        return Collections.singletonList(this.individualFactory.createRandom());
    }
}
