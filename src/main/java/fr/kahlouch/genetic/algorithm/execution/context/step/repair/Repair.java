package fr.kahlouch.genetic.algorithm.execution.context.step.repair;

import fr.kahlouch.genetic.algorithm.vo.Gene;

import java.util.List;
import java.util.function.Function;

public interface Repair<G extends Gene> extends Function<List<G>, List<G>> {
}
