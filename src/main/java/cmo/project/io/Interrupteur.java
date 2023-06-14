package cmo.project.io;

import cmo.project.Composant;
import cmo.project.NonConnecteException;

public class Interrupteur extends Composant {
    protected boolean etat;
    @Override
    public String description() {
        return "(Int@"+this.getId()+")";
    }

    public void on() {etat = true;}
    public void off() {etat = false;}

    public boolean getEtat() throws NonConnecteException {
        return etat;
    }

    @Override
    public void compareTo(Composant composant) {
        // TODO Faire méthode pour sort après avec circuit voir utilisation interface
    }
}
