package cmo.project.logicaldoor;

import cmo.project.Composant;
import cmo.project.NonConnecteException;
import cmo.project.signal.SignalLogic;

public class Or extends Porte2Entrees {
    private Composant in1;
    private Composant in2;
    @Override
    public void setIn1(Composant comp) {
        in1 = comp;
    }

    @Override
    public void setIn2(Composant comp) {
        in2 = comp;
    }

    @Override
    public String description() {
        String s1, s2;
        if(in1 == null){s1 = "non connecte";}
        else{s1 = in1.description();}

        if(in2 == null){s2 = "non connecte";}
        else{s2 = in2.description();}

        return "(Or@"+this.getId() +" in1 : "+ s1 +" in2 : "+ s2 +")";
    }

    @Override
    public SignalLogic getEtat() throws NonConnecteException {
        if (in1 == null || in2 == null) {
            throw new NonConnecteException();
        }
        return in1.getEtat().or(in2.getEtat());
        //return in1.getEtat() || in2.getEtat();
    }
    @Override
    public boolean isInput() {return false;}
    @Override
    public boolean isOutput() {return false;}

    @Override
    public SignalLogic evaluate() {
        return in1.evaluate().or(in2.evaluate());
    }
}
