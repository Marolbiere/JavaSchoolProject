package cmo.project;


interface Comparable {
    void compareTo(Composant composant);
}
public abstract class Composant implements Comparable{

    public String getId() {
        return Integer.toString(hashCode());
    }

    public abstract String description();

    public abstract boolean getEtat() throws NonConnecteException;
}
