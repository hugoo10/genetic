package fr.kahlouch.genetic.algorithm.helper;

import fr.kahlouch.genetic.algorithm.vo.Gene;

public sealed interface CreateGeneCommand<G extends Gene> permits CreateGeneFromGaussianCommand, CreateGeneRandomCommand, CreateGeneCutomCommand {
}
