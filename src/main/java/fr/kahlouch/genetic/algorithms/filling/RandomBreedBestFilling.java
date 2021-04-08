package fr.kahlouch.genetic.algorithms.filling;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Individual;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomBreedBestFilling extends Filling {

    @Override
    List<Individual> doFill(List<Individual> population, List<Individual> selected, GeneticAlgorithmParams params) {
        List<Individual> toBreed = selected.stream()
                .limit(params.fillingRetrieveTopSize)
                .collect(Collectors.toList());
        if (toBreed.isEmpty()) {
            return Collections.singletonList(params.individualFactory.createRandom());
        } else {
            List<Individual> fillingIndividuals = new ArrayList<>();
            for (Individual individual : toBreed) {
                final Individual[] parents = {individual, params.individualFactory.createRandom()};
                for (List<Gene> chromosome : params.mating.mate(parents, params)) {
                    fillingIndividuals.add(params.individualFactory.create(chromosome));
                }
            }
            return fillingIndividuals;
        }
    }
}
