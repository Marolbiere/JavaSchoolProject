package cmo.project.io;

import cmo.project.Composant;
import cmo.project.NonConnecteException;
import cmo.project.signal.SignalLogic;

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

    @Override
    public boolean isInput() {return false;}
    @Override
    public boolean isOutput() {return true;}

    public SignalLogic getEtat() throws NonConnecteException {
        if (in == null) {
            throw new NonConnecteException();
        }
        return in.getEtat();    }

    @Override
    public SignalLogic evaluate() {
        return in.evaluate();
    }
}
