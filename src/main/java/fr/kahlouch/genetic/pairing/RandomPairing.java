package fr.kahlouch.genetic.pairing;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.List;

public class RandomPairing<C extends Chromosome<? extends Gene>> implements Pairing<C> {
    public List<List<C>> pair(Generation<C> generation) {
        List<List<C>> pairs = new ArrayList<>();
        List<C> pair;
        while (pairs.size() < Constants.PAIRING_SIZE / 2) {
            pair = new ArrayList<>();
            pair.add(generation.getChromosomes().get(Constants.RANDOM_GEN.nextInt(Constants.POPULATION_SIZE)));
            pair.add(generation.getChromosomes().get(Constants.RANDOM_GEN.nextInt(Constants.POPULATION_SIZE)));
            pairs.add(pair);
        }
        return pairs;
    }
}
