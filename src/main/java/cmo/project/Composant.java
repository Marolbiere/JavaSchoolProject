package cmo.project;

import cmo.project.exception.NonConnecteException;
import cmo.project.signal.SignalLogic;

public abstract class Composant implements Comparable<Composant>, Evaluable{

    public String getId() {
        return Integer.toString(hashCode());
    }

    public abstract String description();
    public abstract boolean isInput(); // Test si le composant est une entrée pure
    public abstract boolean isOutput(); // Test si le composant est une sortie pure

    @Override
    public int compareTo(Composant c) {
        return this.hashCode() - c.hashCode();
    }

    public abstract SignalLogic getEtat() throws NonConnecteException;
}
