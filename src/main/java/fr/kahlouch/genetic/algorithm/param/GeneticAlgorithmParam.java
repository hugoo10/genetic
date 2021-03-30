package fr.kahlouch.genetic.algorithm.param;

public class GeneticAlgorithmParam {
    public final int populationSize;
    public final int chromosomeSize;
    public final int selectionSize;
    public final int pairingSize;
    public final int fillingRetrieveTopSize;
    public final double mutationOdd;

    GeneticAlgorithmParam(int populationSize, int chromosomeSize, int selectionSize, int pairingSize, int fillingRetrieveTopSize, double mutationOdd) {
        this.populationSize = populationSize;
        this.chromosomeSize = chromosomeSize;
        this.selectionSize = selectionSize;
        this.pairingSize = pairingSize;
        this.fillingRetrieveTopSize = fillingRetrieveTopSize;
        this.mutationOdd = mutationOdd;
    }
}
