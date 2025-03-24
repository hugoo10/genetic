package fr.kahlouch.genetic.algorithms._genetic;

import fr.kahlouch.genetic.population.EvaluatedGeneration;
import fr.kahlouch.genetic.population.EvaluatedIndividual;
import fr.kahlouch.genetic.utils.HistoryType;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class GeneticAlgorithmExecutionHistory {
    private final List<EvaluatedGeneration> bestLineage;
    private final HistoryType type;
    @Nullable
    private EvaluatedIndividual currentBest;
    @Nullable
    private EvaluatedGeneration currentBestGeneration;

    public GeneticAlgorithmExecutionHistory(HistoryType type) {
        this.bestLineage = new ArrayList<>();
        this.type = type;
    }

    public void historize(EvaluatedGeneration evaluatedGeneration) {
        final var evaluatedBest = evaluatedGeneration.getBest();

        final var newBest = currentBest == null || currentBest.fitness() < evaluatedBest.fitness();
        if (newBest) {
            this.currentBest = evaluatedBest;
            this.currentBestGeneration = evaluatedGeneration;
        }

        final boolean addInBestLineage = (HistoryType.ONLY_BEST == this.type && newBest);

        if (addInBestLineage) {
            this.bestLineage.add(evaluatedGeneration);
        }
        if(newBest) {
            System.err.println(evaluatedGeneration);
        }
    }

    public List<EvaluatedGeneration> getBestLineage() {
        return List.copyOf(bestLineage);
    }

    public EvaluatedIndividual getCurrentBest() {
        Objects.requireNonNull(currentBest);
        return currentBest;
    }

    public EvaluatedGeneration getCurrentBestGeneration() {
        Objects.requireNonNull(currentBestGeneration);
        return currentBestGeneration;
    }
}
