package fr.kahlouch.genetic.algorithms.mating;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class TwoPointsMating extends Mating {
    @Override
    protected List<Gene>[] doMate(List<Gene> parentChromosome1, List<Gene> parentChromosome2, GeneticAlgorithmParams params) {
        int splitIndex1 = Constants.RANDOM_GEN.nextInt(parentChromosome1.size());
        int splitIndex2 = Constants.RANDOM_GEN.nextInt(parentChromosome1.size() - splitIndex1) + splitIndex1;

        final List<Gene>[] genes = new List[2];
        genes[0] = new ArrayList<>();
        genes[1] = new ArrayList<>();

        genes[0].addAll(parentChromosome1.subList(0, splitIndex1));
        genes[0].addAll(parentChromosome2.subList(splitIndex1, splitIndex2));
        genes[0].addAll(parentChromosome1.subList(splitIndex2, parentChromosome1.size()));

        genes[1].addAll(parentChromosome2.subList(0, splitIndex1));
        genes[1].addAll(parentChromosome1.subList(splitIndex1, splitIndex2));
        genes[1].addAll(parentChromosome2.subList(splitIndex2, parentChromosome2.size()));

        params.mutation.mutateList(genes[0], params);
        params.mutation.mutateList(genes[1], params);
        return genes;
    }
}
