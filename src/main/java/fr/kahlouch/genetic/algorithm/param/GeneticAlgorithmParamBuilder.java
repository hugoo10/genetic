package fr.kahlouch.genetic.algorithm.param;

import fr.kahlouch.genetic.algorithm.param.exception.GeneticAlgorithmParamBuilderException;

public final class GeneticAlgorithmParamBuilder {
    private int populationSize;
    private int chromosomeSize;
    private int selectionSize;
    private int pairingSize;
    private int fillingRetrieveTopSize;
    private double mutationOdd;

    public GeneticAlgorithmParamBuilder populationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public GeneticAlgorithmParamBuilder chromosomeSize(int chromosomeSize) {
        this.chromosomeSize = chromosomeSize;
        return this;
    }

    public GeneticAlgorithmParamBuilder selectionSize(int selectionSize) {
        this.selectionSize = selectionSize;
        return this;
    }

    public GeneticAlgorithmParamBuilder pairingSize(int pairingSize) {
        this.pairingSize = pairingSize;
        return this;
    }

    public GeneticAlgorithmParamBuilder fillingRetrieveTopSize(int fillingRetrieveTopSize) {
        this.fillingRetrieveTopSize = fillingRetrieveTopSize;
        return this;
    }

    public GeneticAlgorithmParamBuilder mutationOdd(double mutationOdd) {
        this.mutationOdd = mutationOdd;
        return this;
    }

    public void validate() {
        if (this.populationSize <= 0)
            throw new GeneticAlgorithmParamBuilderException("The population size need to be over 0");
        if (this.chromosomeSize <= 0)
            throw new GeneticAlgorithmParamBuilderException("The chromosome size need to be over 0");
        if (this.selectionSize <= 0)
            throw new GeneticAlgorithmParamBuilderException("The selection size need to be over 0");
        if (this.pairingSize <= 0)
            throw new GeneticAlgorithmParamBuilderException("The pairing size need to be over 0");
        if (this.fillingRetrieveTopSize <= 0)
            throw new GeneticAlgorithmParamBuilderException("The filling retrieve top size need to be over 0");
        if (this.mutationOdd <= 0)
            throw new GeneticAlgorithmParamBuilderException("The mutation odd need to be over 0");
    }

    public GeneticAlgorithmParam build() {
        this.validate();
        return new GeneticAlgorithmParam(this.populationSize, this.chromosomeSize, this.selectionSize, this.pairingSize, this.fillingRetrieveTopSize, this.mutationOdd);
    }
}
