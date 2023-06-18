package cmo.project.main;

import cmo.project.Circuit;
import cmo.project.CommandLineInterface;
import cmo.project.Composant;
import cmo.project.exception.NonChoiceException;
import cmo.project.exception.NonConnecteException;
import cmo.project.io.Interrupteur;
import cmo.project.io.Vanne;
import cmo.project.logicaldoor.And;
import cmo.project.logicaldoor.Not;
import cmo.project.logicaldoor.Or;

public class ExampleCircuits {
    public static void traceEtats(Composant[] tabComposant) {
        for (Composant composant : tabComposant) {
            System.out.println(composant.description());
            try {
                System.out.println(composant.getEtat());
            } catch (NonConnecteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void example(Circuit circ) {
        System.out.println(circ.nomenclature());
        circ.description();
        for (Interrupteur circI : circ.getInputs()) {
            System.out.println(circI.getId());
        }
        for (Vanne circO : circ.getOutputs()) {
            System.out.println(circO.getId());
        }

        circ.getInputs().get(0).on();
        circ.getInputs().get(2).on();

        circ.traceEtats();
    }

    public static boolean safeChoose(Circuit circ, Interrupteur inte) {
        //Gestion exception : si l'utilisateur ne rentre pas la plage de valeurs demandée
        try {
            return inte.setSelfEtat();
        } catch (NonChoiceException e) {
            System.out.println(e.getMessage());
            return safeChoose(circ, inte);
        }
    }


    public static void circuitEvaluation(Circuit circ, CommandLineInterface cli) {
        for(Interrupteur inte : circ.getInputs()) {
            safeChoose(circ, inte);
        }
        System.out.println("La sortie de votre circuit sera : "+ circ.evaluate());
    }
    public static void main(String[] args) {
        //Création du tableau de composant pour stocker nos composant
        Composant[] tabComposant = new Composant[7];

        // Création des interrupteurs
        Interrupteur i1 = new Interrupteur("interrupteur 1");
        tabComposant[0] = i1;
        Interrupteur i2 = new Interrupteur("interrupteur 2");
        tabComposant[1] = i2;
        Interrupteur is = new Interrupteur("interrupteur securite");
        tabComposant[2] = is;
        // Création de la porte Not,Or et And;
        Or o = new Or();
        tabComposant[3] = o;
        Not n = new Not();
        tabComposant[4] = n;
        And a = new And();
        tabComposant[5] = a;

        // Création de la Vanne
        Vanne v = new Vanne();
        tabComposant[6] = v;

        // Connexion de tous les composants (cf : la figure dans le pdf du projet)
        o.setIn1(i1);
        o.setIn2(i2);
        n.setIn(is);
        a.setIn1(o);
        a.setIn2(n);
        v.setIn(a);

        // traceEtats(tabComposant); // <-- Question 2.2

        // Création du circuit avec tous nos composants
        Circuit circ = new Circuit("circuit 1", tabComposant);

        // example(circ); // <-- Question 3.2

        CommandLineInterface cli = new CommandLineInterface();

        circuitEvaluation(circ, cli); // <-- Question 4.3

    }
}
