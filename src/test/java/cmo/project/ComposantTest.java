package cmo.project;

import cmo.project.logicaldoor.*;
import cmo.project.io.*;
import cmo.project.signal.SignalHaut;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComposantTest {

    @Test
    public void testDescription() {

        //Tests composant pas d'entrée
        Interrupteur i = new Interrupteur();
        assertEquals("(Int@"+i.hashCode()+")", i.description());

        //Test composants 1 entrée
        Vanne v = new Vanne();
        assertEquals("(Van@"+ v.hashCode() +" in : non connecte)", v.description());
        v.setIn(i);
        assertEquals("(Van@"+ v.hashCode() +" in : "+i.description()+")", v.description());

        Not n = new Not();
        assertEquals("(Not@"+ n.hashCode() +" in : non connecte)", n.description());
        n.setIn(i);
        assertEquals("(Not@"+ n.hashCode() +" in : "+i.description()+")", n.description());

        //Test composant 2 entrée
        Or or = new Or();
        assertEquals("(Or@"+ or.hashCode() +" in1 : non connecte in2 : non connecte)", or.description());
        or.setIn1(i);
        assertEquals("(Or@"+ or.hashCode() +" in1 : "+i.description()+" in2 : non connecte)", or.description());
        Interrupteur i1 = new Interrupteur();
        or.setIn2(i1);
        assertEquals("(Or@"+ or.hashCode() +" in1 : "+i.description()+" in2 : " + i1.description()+")", or.description());

        And and = new And();
        assertEquals("(And@"+ and.hashCode() +" in1 : non connecte in2 : non connecte)", and.description());
        and.setIn1(i);
        assertEquals("(And@"+ and.hashCode() +" in1 : "+i.description()+" in2 : non connecte)", and.description());
        and.setIn2(i1);
        assertEquals("(And@"+ and.hashCode() +" in1 : "+i.description()+" in2 : " + i1.description()+")", and.description());
    }

    @Test
    public void testGetEtatSansException() throws NonConnecteException{
        //getEtat de l'interrupteur
        Interrupteur i = new Interrupteur();
        i.on();
        assertEquals(true, i.getEtat().value());
        i.off();
        assertEquals(false, i.getEtat().value());

        //getEtat de la Vanne
        Vanne v = new Vanne();
        v.setIn(i);
        assertEquals(false, v.getEtat().value());
        i.on();
        assertEquals(true, v.getEtat().value());

        //getEtat du NOT
        Not n = new Not();
        n.setIn(i);
        assertEquals(false, n.getEtat().value());
        i.off();
        assertEquals(true, n.getEtat().value());

        //getEtat du AND
        And a = new And();
        Interrupteur i1 = new Interrupteur();
        a.setIn1(i);
        a.setIn2(i1);
        i.on();
        i1.on();
        assertEquals(true, a.getEtat().value());
        i.off();
        assertEquals(false, a.getEtat().value());
        i1.off();
        assertEquals(false, a.getEtat().value());

        //getEtat du OR
        Or o = new Or();
        o.setIn1(i);
        o.setIn2(i1);
        i.on();
        i1.on();
        assertEquals(true, o.getEtat().value());
        i.off();
        assertEquals(true, o.getEtat().value());
        i1.off();
        assertEquals(false, o.getEtat().value());

    }
    @Test
    public void testGetEtatAvecException() {
        Interrupteur i = new Interrupteur();

        //getEtat de la Vanne
        Vanne v = new Vanne();
        Exception e = assertThrows(NonConnecteException.class, () -> {
            v.getEtat();
        });
        assertEquals("Entree non connecte au composant", e.getMessage());

        //getEtat du NOT
        Not n = new Not();
        e = assertThrows(NonConnecteException.class, () -> {
            n.getEtat();
        });
        assertEquals("Entree non connecte au composant", e.getMessage());

        //getEtat du AND
        And a1 = new And();
        e = assertThrows(NonConnecteException.class, () -> {
            a1.getEtat();
        });
        assertEquals("Entree non connecte au composant", e.getMessage());

        a1.setIn1(i);
        e = assertThrows(NonConnecteException.class, () -> {
            a1.getEtat();
        });
        assertEquals("Entree non connecte au composant", e.getMessage());

        And a2 = new And();
        a2.setIn2(i);
        e = assertThrows(NonConnecteException.class, () -> {
            a2.getEtat();
        });
        assertEquals("Entree non connecte au composant", e.getMessage());

        //getEtat du OR
        Or o1 = new Or();
        e = assertThrows(NonConnecteException.class, () -> {
            o1.getEtat();
        });
        assertEquals("Entree non connecte au composant", e.getMessage());

        o1.setIn1(i);
        e = assertThrows(NonConnecteException.class, () -> {
            o1.getEtat();
        });
        assertEquals("Entree non connecte au composant", e.getMessage());

        Or o2 = new Or();
        o2.setIn2(i);
        e = assertThrows(NonConnecteException.class, () -> {
            o2.getEtat();
        });
        assertEquals("Entree non connecte au composant", e.getMessage());
    }




}
