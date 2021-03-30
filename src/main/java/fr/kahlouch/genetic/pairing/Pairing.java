package fr.kahlouch.genetic.pairing;

import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.population.Generation;

import java.util.List;

public interface Pairing<C extends Chromosome<? extends Gene>> {
    List<List<C>> pair(Generation<C> generation);
}
