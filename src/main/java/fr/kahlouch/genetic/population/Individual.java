package fr.kahlouch.genetic.population;

import java.util.List;


public sealed interface Individual permits NewBornIndividual, EvaluatedIndividual {
    List<Gene> getChromosome();

}
