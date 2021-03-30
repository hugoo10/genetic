package fr.kahlouch.genetic.genetic;

import java.util.SplittableRandom;

//https://towardsdatascience.com/continuous-genetic-algorithm-from-scratch-with-python-ff29deedd099
public final class Constants {
    private Constants() {
    }

    public static final SplittableRandom RANDOM_GEN = new SplittableRandom(System.nanoTime());
    public static int POPULATION_SIZE = 100;
    public static int CHROMOSOME_SIZE = 150;
    public static int SELECTION_SIZE = 20;
    public static int PAIRING_SIZE = 80;
    public static int FILLING_RETRIEVE_TOP_SIZE = 5;
    public static double MUTATION_ODD = 0.1;
}
