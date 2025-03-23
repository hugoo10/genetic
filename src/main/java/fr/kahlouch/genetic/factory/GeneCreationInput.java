package fr.kahlouch.genetic.factory;

public sealed interface GeneCreationInput permits RandomGene, GaussianGeneCreationInput, CustomGeneCreationInput{
}
