package fr.kahlouch.genetic.algorithms.filling;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Individual;

import java.util.List;

public enum FillingType {
    RANDOM_BREED_BEST(new RandomBreedBestFilling()), RANDOM(new RandomFilling());

    Filling filling;

    FillingType(Filling filling) {
        this.filling = filling;
    }

    public void fill(List<Individual> population, List<Individual> selected, GeneticAlgorithmParams params) {
        this.filling.fill(population, selected, params);
    }
}
