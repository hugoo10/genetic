package fr.kahlouch.genetic.algorithm.execution.context.step.repair;

import fr.kahlouch.genetic.algorithm.execution.context.step.ExecutionStep;
import fr.kahlouch.genetic.algorithm.vo.Gene;

import java.util.List;
import java.util.function.Function;

public abstract class Repair<G extends Gene> extends ExecutionStep<List<G>, List<G>> {

    public static <G extends Gene> Repair<G> noop() {
        return new Repair<>() {
            @Override
            public List<G> apply(List<G> genes) {
                return genes;
            }
        };
    }

    public static <G extends Gene> Repair<G> of(Function<List<G>, List<G>> function) {
        return new Repair<>() {
            @Override
            public List<G> apply(List<G> genes) {
                return function.apply(genes);
            }
        };
    }
}
