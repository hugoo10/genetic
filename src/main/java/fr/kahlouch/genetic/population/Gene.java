package fr.kahlouch.genetic.population;

import java.util.List;

public abstract class Gene<G extends Gene> {


    public abstract List<G> breed(G mate, double random);
}
