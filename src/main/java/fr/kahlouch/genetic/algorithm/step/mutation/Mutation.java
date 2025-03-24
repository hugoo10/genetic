package fr.kahlouch.genetic.algorithm.step.mutation;

import fr.kahlouch.genetic.algorithm.vo.Gene;

import java.util.List;
import java.util.function.Function;

public interface Mutation<G extends Gene> extends Function<G, G> {
    default List<G> apply(List<G> genes) {
        return genes.stream()
                .map(this)
                .toList();
    }
}
