package fr.kahlouch.genetic.algorithm;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;

public interface ExecutionGenerationHelper<G extends Gene, I extends Individual<G, T>, T> {
    I createIndividual(List<G> genes);

    G breedGenes(G gene1, G gene2, double random);

    long eliteGroupSize();
}
