package fr.kahlouch.genetic.algorithms.mating;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SinglePointMating extends Mating {
    @Override
    protected List<Gene>[] doMate(List<Gene> parentChromosome1, List<Gene> parentChromosome2, GeneticAlgorithmParams params) {
        int splitIndex = Constants.RANDOM_GEN.nextInt(parentChromosome1.size());

        final List<Gene>[] genes = new List[2];
        genes[0] = new ArrayList<>();
        genes[1] = new ArrayList<>();

        genes[0].addAll(parentChromosome1.subList(0, splitIndex));
        genes[0].addAll(parentChromosome2.subList(splitIndex, parentChromosome2.size()));

        genes[1].addAll(parentChromosome2.subList(0, splitIndex));
        genes[1].addAll(parentChromosome1.subList(splitIndex, parentChromosome1.size()));

        params.mutation.mutateList(genes[0], params);
        params.mutation.mutateList(genes[1], params);
        return genes;
    }
}
