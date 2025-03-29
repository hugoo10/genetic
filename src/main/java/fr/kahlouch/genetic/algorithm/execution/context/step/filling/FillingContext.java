package fr.kahlouch.genetic.algorithm.execution.context.step.filling;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;

public record FillingContext<G extends Gene, I extends Individual<G, T>, T>(List<I> individuals,
                                                                            int newPopulationSize) {
}
