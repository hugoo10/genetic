package fr.kahlouch.genetic.algorithms.mating;

import fr.kahlouch.genetic.GeneticAlgorithmParams;
import fr.kahlouch.genetic.population.Gene;
import fr.kahlouch.genetic.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ContinuousMating extends Mating {
    @Override
    protected List<Gene>[] doMate(List<Gene> parentChromosome1, List<Gene> parentChromosome2, GeneticAlgorithmParams params) {
        final double random = Constants.RANDOM_GEN.nextDouble();
        final List<Gene>[] genes = new List[2];
        genes[0] = new ArrayList<>();
        genes[1] = new ArrayList<>();

        for (int i = 0; i < parentChromosome1.size(); ++i) {
            Gene[] breedGenes = parentChromosome1.get(i).breed(parentChromosome2.get(i), random);
            genes[0].add(params.mutation.mutate(breedGenes[0], params));
            genes[1].add(params.mutation.mutate(breedGenes[1], params));
        }
        return genes;
    }
}
