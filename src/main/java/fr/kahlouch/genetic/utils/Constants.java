package fr.kahlouch.genetic.utils;

import java.util.SplittableRandom;

public final class Constants {
    private Constants() {
    }

    public static final SplittableRandom RANDOM_GEN = new SplittableRandom(System.nanoTime());
}
