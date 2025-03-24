package fr.kahlouch.genetic.algorithm.step.selection;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;

import java.util.List;
import java.util.function.Function;

public interface Selection<G extends Gene, I extends Individual<G, T>, T> extends Function<List<I>, List<Parents<I>>> {
}
