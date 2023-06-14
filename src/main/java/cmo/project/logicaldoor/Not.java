package cmo.project.logicaldoor;

import cmo.project.Composant;
import cmo.project.NonConnecteException;

public class Not extends Porte {
    private Composant in;
    public void setIn(Composant comp) {
        in = comp;
    }

    @Override
    public String description() {
        String s;
        if(in == null){s = "non connecte";}
        else{s = in.description();}
        return "(Not@"+this.getId() +" in : "+ s+")";
    }
    public boolean getEtat() throws NonConnecteException {
        if (in == null) {
            throw new NonConnecteException();
        }
        return !in.getEtat();
    }

    @Override
    public void compareTo(Composant composant) {
        // TODO Faire méthode pour sort après avec circuit voir utilisation interface
    }
}
