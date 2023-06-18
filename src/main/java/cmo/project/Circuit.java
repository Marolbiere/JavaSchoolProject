package cmo.project;

import cmo.project.exception.NonConnecteException;
import cmo.project.io.Interrupteur;
import cmo.project.io.Vanne;
import cmo.project.signal.SignalHaut;
import cmo.project.signal.SignalLogic;

import java.util.*;

public class Circuit implements Evaluable {
    private String nom;
    private List<Composant> cps = new ArrayList<>();

    public Circuit(String nom, Composant[] cps) {
        this.nom = nom;
        this.cps.addAll(Arrays.asList(cps));
        Collections.sort(this.cps);
    }

    public String getNom() {return this.nom;}

    public List<String> nomenclature() {
        List<String > tmp = new ArrayList<>();
        for (Composant c : cps) {
            try {
                tmp.add(c.getId());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
        return tmp;
    }

    public void description() {
        System.out.println("Nom : "+this.nom);
        for (Composant c : cps) {
            c.description();
        }
    }

    public void traceEtats() {
        System.out.println("Nom : "+this.nom);
        for(Composant c : cps) {
            try {
                System.out.println(c.getEtat());
            } catch(NonConnecteException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public List<Interrupteur> getInputs() {
        List<Interrupteur> tmp = new ArrayList<>();
        for(Composant c : cps) {
            if (c.isInput()) {
                tmp.add((Interrupteur) c);
            }
        }
        return tmp;
    }

    public List<Vanne> getOutputs() {
        List<Vanne> tmp = new ArrayList<>();
        for (Composant c : cps) {
            if (c.isOutput()) {
                tmp.add((Vanne) c);
            }
        }
        return tmp;
    }


    @Override
    public SignalLogic evaluate() {
        SignalLogic sh = new SignalHaut();
        for(Vanne v: this.getOutputs())
        {
           sh = sh.and(v.evaluate());
        }
        return sh;
    }
}
