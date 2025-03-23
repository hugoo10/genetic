package fr.kahlouch.genetic.factory;

import fr.kahlouch.genetic.population.Gene;

public record GaussianGeneCreationInput(Gene gene, double gaussian) implements GeneCreationInput {
}
