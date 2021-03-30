package fr.kahlouch.genetic.pairing;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.List;

public class RandomPairing<C extends Chromosome<? extends Gene>> extends Pairing<C> {
    public List<List<C>> pair(Generation<C> generation) {
        List<List<C>> pairs = new ArrayList<>();
        List<C> pair;
        while (pairs.size() < this.params.pairingSize / 2) {
            pair = new ArrayList<>();
            pair.add(generation.getChromosomes().get(Constants.RANDOM_GEN.nextInt(this.params.populationSize)));
            pair.add(generation.getChromosomes().get(Constants.RANDOM_GEN.nextInt(this.params.populationSize)));
            pairs.add(pair);
        }
        return pairs;
    }
}
