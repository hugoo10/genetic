package fr.kahlouch.genetic.algorithms.selection;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class RandomSelection extends Selection {

    @Override
    protected List<Individual> select(List<Individual> individuals, GeneticAlgorithmParams params) {
        final List<Individual> selected = new ArrayList<>();
        for (int i = 0; i < params.selectionSize; ++i) {
            selected.add(individuals.get(Constants.RANDOM_GEN.nextInt(individuals.size())));
        }
        return selected;
    }
}
