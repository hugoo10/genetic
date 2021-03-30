package fr.kahlouch.genetic.filling;

import fr.kahlouch.genetic.Constants;
import fr.kahlouch.genetic.factory.AbstractChromosomeFactory;
import fr.kahlouch.genetic.mating.Mating;
import fr.kahlouch.genetic.population.Chromosome;
import fr.kahlouch.genetic.population.Gene;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RandomBreedBestFilling<C extends Chromosome<G>, G extends Gene> extends Filling<C, G> {

    @Override
    List<C> doFill(List<C> futurGeneration) {
        List<C> toBreed = futurGeneration.stream()
                .filter(chromosome -> chromosome.getChromosomeType() == Chromosome.ChromosomeType.SELECTED)
                .limit(this.params.fillingRetrieveTopSize)
                .collect(Collectors.toList());
        if (toBreed.isEmpty()) {
            return Collections.singletonList(this.chromosomeFactory.createRandom());
        } else {
            List<C> fillingChromosomes = new ArrayList<>();
            for (C chromosome : toBreed) {
                fillingChromosomes.addAll(mating.mate(chromosome, this.chromosomeFactory.createRandom()));
            }
            return fillingChromosomes;
        }
    }
}
