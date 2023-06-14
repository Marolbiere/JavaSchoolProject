package cmo.project.io;

import cmo.project.Composant;
import cmo.project.NonConnecteException;

public class Vanne extends Composant {
    private Composant in;
    public void setIn(Composant composant) {
        in = composant;
    }
    @Override
    public String description() {
        String s;
        if(in == null){s = "non connecte";}
        else{s = in.description();}
        return "(Van@"+this.getId() +" in : "+ s+")";
    }

    public boolean getEtat() throws NonConnecteException {
        if (in == null) {
            throw new NonConnecteException();
        }
        return in.getEtat();
    }

    @Override
    public void compareTo(Composant composant) {
        // TODO Faire méthode pour sort après avec circuit voir utilisation interface
    }
}
