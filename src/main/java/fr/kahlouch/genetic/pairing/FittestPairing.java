package fr.kahlouch.genetic.pairing;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FittestPairing<C extends Chromosome<? extends Gene>> extends Pairing<C> {
    public List<List<C>> pair(Generation<C> generation) {
        generation.getChromosomes().sort(Comparator.comparingDouble(Chromosome::getFitness));
        List<List<C>> pairs = new ArrayList<>();
        List<C> pair;
        int chromosomeIndex = this.params.populationSize - 1;
        while (pairs.size() < this.params.pairingSize / 2) {
            pair = new ArrayList<>();
            pair.add(generation.getChromosomes().get(chromosomeIndex--));
            pair.add(generation.getChromosomes().get(chromosomeIndex--));
            pairs.add(pair);
        }
        return pairs;
    }
}
