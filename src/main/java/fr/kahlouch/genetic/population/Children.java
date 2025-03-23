package fr.kahlouch.genetic.population;

import java.util.Objects;
import java.util.stream.Stream;

public record Children(NewBornIndividual child1, NewBornIndividual child2) {
    public Children {
        Objects.requireNonNull(child1);
        Objects.requireNonNull(child2);
    }

    public Stream<NewBornIndividual> stream() {
        return Stream.of(child1, child2);
    }
}
