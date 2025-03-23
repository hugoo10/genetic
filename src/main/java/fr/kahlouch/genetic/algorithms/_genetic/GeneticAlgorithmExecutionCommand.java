package fr.kahlouch.genetic.algorithms._genetic;

import fr.kahlouch.genetic.population.NewBornGeneration;
import fr.kahlouch.genetic.utils.HistoryType;
import org.jspecify.annotations.Nullable;

import java.time.Duration;
import java.util.Objects;

public class GeneticAlgorithmExecutionCommand {
    private final NewBornGeneration firstPopulation;
    private final HistoryType historyType;
    private final Double fitnessCap;
    @Nullable
    private final Duration timeCap;

    private GeneticAlgorithmExecutionCommand(NewBornGeneration firstPopulation, HistoryType historyType, Double fitnessCap, @Nullable Duration timeCap) {
        Objects.requireNonNull(firstPopulation);
        Objects.requireNonNull(historyType);
        Objects.requireNonNull(fitnessCap);

        this.firstPopulation = firstPopulation;
        this.historyType = historyType;
        this.fitnessCap = fitnessCap;
        this.timeCap = timeCap;
    }


    public NewBornGeneration getFirstPopulation() {
        return firstPopulation;
    }

    public HistoryType getHistoryType() {
        return historyType;
    }

    public Double getFitnessCap() {
        return fitnessCap;
    }

    public @Nullable Duration getTimeCap() {
        return timeCap;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder()
                .firstPopulation(this.firstPopulation)
                .historyType(this.historyType)
                .fitnessCap(this.fitnessCap)
                .timeCap(this.timeCap);
    }


    public static class Builder {
        @Nullable
        private NewBornGeneration firstPopulation;
        @Nullable
        private HistoryType historyType;
        @Nullable
        private Double fitnessCap;
        @Nullable
        private Duration timeCap;

        public Builder firstPopulation(NewBornGeneration firstPopulation) {
            this.firstPopulation = firstPopulation;
            return this;
        }

        public Builder historyType(HistoryType historyType) {
            this.historyType = historyType;
            return this;
        }

        public Builder fitnessCap(Double fitnessCap) {
            this.fitnessCap = fitnessCap;
            return this;
        }

        public Builder timeCap(Duration timeCap) {
            this.timeCap = timeCap;
            return this;
        }

        public GeneticAlgorithmExecutionCommand build() {
            Objects.requireNonNull(this.firstPopulation);
            Objects.requireNonNull(this.historyType);
            Objects.requireNonNull(this.fitnessCap);
            return new GeneticAlgorithmExecutionCommand(this.firstPopulation, this.historyType, this.fitnessCap, this.timeCap);
        }
    }
}
