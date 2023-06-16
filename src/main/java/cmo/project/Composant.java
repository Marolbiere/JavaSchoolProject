package cmo.project;

import cmo.project.signal.SignalLogic;

public abstract class Composant implements Comparable<Composant>, Evaluable{

    public String getId() {
        return Integer.toString(hashCode());
    }

    public abstract String description();
    public abstract boolean isInput(); //Entrée pur
    public abstract boolean isOutput(); //Sortie pur

    @Override
    public int compareTo(Composant c) {
        return this.hashCode() - c.hashCode();
    }

    public abstract SignalLogic getEtat() throws NonConnecteException;
}
