module Genetic {
    requires org.jspecify;
    requires javafx.base;
    exports fr.kahlouch.genetic.algorithm.step.crossover;
    exports fr.kahlouch.genetic.algorithm.step.mutation;
    exports fr.kahlouch.genetic.algorithm.step.selection;
    exports fr.kahlouch.genetic.algorithm.step.elitism;
    exports fr.kahlouch.genetic.algorithm.vo;
    exports fr.kahlouch.genetic.algorithm;
}