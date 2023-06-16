package cmo.project.io;

import cmo.project.CommandLineInterface;
import cmo.project.Composant;
import cmo.project.NonChoiceException;
import cmo.project.NonConnecteException;
import cmo.project.signal.SignalBas;
import cmo.project.signal.SignalHaut;
import cmo.project.signal.SignalLogic;

import java.lang.reflect.Parameter;

public class Interrupteur extends Composant {
    private String nom;
    protected SignalLogic etat;

    public Interrupteur(String nom) {
        this.nom = nom;
    }
    public Interrupteur() {
        this.nom = "Pas de nom renseigne";
    }

    @Override
    public String description() {
        return "(Int@"+this.getId()+")";
    }

    public void on() {etat = new SignalHaut();}
    public void off() {etat = new SignalBas();}

    public SignalLogic getEtat() throws NonConnecteException {
        return etat;
    }
    @Override
    public boolean isInput() {return true;}
    @Override
    public boolean isOutput() {return false;}

    @Override
    public SignalLogic evaluate() {
        return etat;
    }


    public boolean setSelfEtat() throws NonChoiceException {
        CommandLineInterface cli = new CommandLineInterface();
        System.out.print("Veuillez choisir l'état (0 ou 1) de l'"+this.nom+" : ");
        String state = cli.scanCommand();
        if (Integer.parseInt(state) > 1 | Integer.parseInt(state) < 0) {
            throw new NonChoiceException();
        }
        if (Integer.parseInt(state)==1) {
            this.on();
        } else {
            this.off();
        }
        return true;
    }
}
