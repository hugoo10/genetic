package fr.kahlouch.genetic.algorithms.selection;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;

import java.util.List;

public enum SelectionType {
    FITTEST(new FittestSelection()),
    RANDOM(new RandomSelection()),
    ROULETTE_WHEEL(new RouletteWheelSelection());

    Selection selection;

    SelectionType(Selection selection) {
        this.selection = selection;
    }

    public List<Individual> select(List<Individual> individuals, GeneticAlgorithmParams params) {
        return this.selection.select(individuals, params);
    }
}
