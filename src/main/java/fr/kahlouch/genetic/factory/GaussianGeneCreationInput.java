package fr.kahlouch.genetic.factory;

import fr.kahlouch.genetic.population.Gene;

public record GaussianGeneCreationInput<G extends Gene>(G gene, double gaussian) implements GeneCreationInput {
}
