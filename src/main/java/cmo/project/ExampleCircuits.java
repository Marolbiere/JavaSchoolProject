package cmo.project;

import cmo.project.io.Interrupteur;
import cmo.project.io.Vanne;
import cmo.project.logicaldoor.And;
import cmo.project.logicaldoor.Not;
import cmo.project.logicaldoor.Or;
import com.sun.tools.javac.Main;

import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class ExampleCircuits {


    public static void traceEtats(Composant[] tabComposant) {
        for(int i=0; i<tabComposant.length; i++) {
            System.out.println(tabComposant[i].description());
            try {
                System.out.println(tabComposant[i].getEtat());
            }catch (NonConnecteException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Composant[] tabComposant = new Composant[7];

        Interrupteur i1 = new Interrupteur();
        tabComposant[0] = i1;
        Interrupteur i2 = new Interrupteur();
        tabComposant[1] = i2;
        Interrupteur is = new Interrupteur();
        tabComposant[2] = is;


        Or o = new Or();
        tabComposant[3] = o;
        Not n = new Not();
        tabComposant[4] = n;
        And a = new And();
        tabComposant[5] = a;

        Vanne v = new Vanne();
        tabComposant[6] = v;

        o.setIn1(i1);
        o.setIn2(i2);
        n.setIn(is);
        a.setIn1(o);
        a.setIn2(n);
        v.setIn(a);

        traceEtats(tabComposant);
    }
}
