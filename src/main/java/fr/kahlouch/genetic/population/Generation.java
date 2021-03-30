package fr.kahlouch.genetic.population;

import java.util.ArrayList;
import java.util.List;

public class Generation<C extends Chromosome<? extends Gene>> {
    protected List<C> chromosomes = new ArrayList<>();

    public Generation(List<C> chromosomes) {
        this.chromosomes = chromosomes;
    }

    public List<C> getChromosomes() {
        return chromosomes;
    }
}
