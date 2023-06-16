package cmo.project.logicaldoor;

import cmo.project.Composant;
import cmo.project.NonConnecteException;
import cmo.project.signal.SignalLogic;

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
    public SignalLogic getEtat() throws NonConnecteException {
        if (in == null) {
            throw new NonConnecteException();
        }
        return in.getEtat().not();
        //return !in.getEtat();
    }

    @Override
    public boolean isInput() {return false;}
    @Override
    public boolean isOutput() {return false;}

    @Override
    public SignalLogic evaluate() {
        return in.evaluate().not();
    }

}
