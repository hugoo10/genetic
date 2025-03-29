package fr.kahlouch.genetic.algorithm.execution.listener;

import fr.kahlouch.genetic.algorithm.vo.Gene;
import fr.kahlouch.genetic.algorithm.vo.Individual;
import fr.kahlouch.genetic.algorithm.vo.Population;

import java.io.*;
import java.util.function.Consumer;
import java.util.logging.Logger;

public final class BestIndividualWriter<G extends Gene, I extends Individual<G, T>, T> implements ExecutionListener<G, I, T> {
    private final Consumer<String> stringConsumer;
    private I currentBest;

    public BestIndividualWriter() {
        final var logger = Logger.getLogger(BestIndividualWriter.class.getSimpleName());
        this.stringConsumer = logger::info;
    }

    public BestIndividualWriter(OutputStream outputStream) {
        this(new OutputStreamWriter(outputStream));
    }

    public BestIndividualWriter(Writer writer) {
        final var bufferedWriter = new BufferedWriter(writer);
        this.stringConsumer = value -> {
            try {
                bufferedWriter.write(value);
            } catch (IOException ioException) {
                throw new RuntimeException(ioException);
            }
        };
    }


    @Override
    public void send(Population<G, I, T> population) {
        final var contender = population.getBest();

        if (currentBest == null || currentBest.compareTo(contender) < 0) {
            currentBest = contender;
            this.stringConsumer.accept(population.getId() + ": " + this.currentBest);
        }
    }
}
