package fr.kahlouch.genetic.algorithm.exception;

public class GeneticAlgorithmBuilderException extends RuntimeException {
    public GeneticAlgorithmBuilderException(Class<?> missingImplementation) {
        super("Please specify an implementation for class: " + missingImplementation);
    }
}
