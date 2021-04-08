package fr.kahlouch.genetic.population;

public interface Gene {
    Gene[] breed(Gene gene, double random);
}
