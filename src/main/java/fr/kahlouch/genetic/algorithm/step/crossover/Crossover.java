package fr.kahlouch.genetic.algorithm.step.crossover;

import fr.kahlouch.genetic.algorithm.step.selection.Parents;
import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;
import java.util.function.Function;

public interface Crossover<G extends Gene, I extends Individual<G, T>, T> extends Function<Parents<G, I, T>, List<List<G>>> {
}
