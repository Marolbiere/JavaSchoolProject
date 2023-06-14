package cmo.project;

import cmo.project.io.Interrupteur;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Circuit  {
    private String nom;
    private List<Composant> cps;

    public Circuit(String nom, Composant[] cps) {
        this.nom = nom;
        this.cps.addAll(Arrays.asList(cps));
        // TODO A TRIER EN RAJOUTANT INTERFACE COMPARABLE A COMPOSANT
    }

    public List<String> nomenclature() {
        List<String > tmp = null;
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
        List<Interrupteur> tmp = null;
        for(Composant c : cps) {
            // TODO | trouvez un moyen, en parcourant la liste des composants du circuit, de stocker uniquement
            // TODO | les interrupteurs dans cette liste :
            // TODO |   — sans utiliser de test if sur le type pour chaque composant,
            // TODO |   — sans utiliser la méthode instanceof.
        }
        return tmp;
    }

    /*public List<Vanne> getOutputs() {

    }*/




}
