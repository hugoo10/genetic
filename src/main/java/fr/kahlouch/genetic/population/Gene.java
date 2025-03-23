package fr.kahlouch.genetic.population;

public interface Gene {
    BreededGene breed(Gene gene, double random);
}
