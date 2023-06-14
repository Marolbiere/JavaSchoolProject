package cmo.project;

import cmo.project.logicaldoor.*;
import cmo.project.io.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ComposantTest {

    @Test
    public void testDescription() {

        //Tests composant pas d'entrée
        Interrupteur i = new Interrupteur();
        assertEquals("Int@"+i.hashCode(), i.description());

        //Test composants 1 entrée
        Vanne v = new Vanne();
        assertEquals("Van@"+ v.hashCode() +" in : non connecte", v.description());
        v.setIn(i);
        assertEquals("Van@"+ v.hashCode() +" in : "+i.description(), v.description());

        Not n = new Not();
        assertEquals("Not@"+ n.hashCode() +" in : non connecte", n.description());
        n.setIn(i);
        assertEquals("Not@"+ n.hashCode() +" in : "+i.description(), n.description());

        //Test composant 2 entrée
        Or or = new Or();
        assertEquals("Or@"+ or.hashCode() +" in1 : non connecte in2 : non connecte", or.description());
        or.setIn1(i);
        assertEquals("Or@"+ or.hashCode() +" in1 : "+i.description()+" in2 : non connecte", or.description());
        Interrupteur i1 = new Interrupteur();
        or.setIn2(i1);
        assertEquals("Or@"+ or.hashCode() +" in1 : "+i.description()+" in2 : " + i1.description(), or.description());

        And and = new And();
        assertEquals("And@"+ and.hashCode() +" in1 : non connecte in2 : non connecte", and.description());
        and.setIn1(i);
        assertEquals("And@"+ and.hashCode() +" in1 : "+i.description()+" in2 : non connecte", and.description());
        and.setIn2(i1);
        assertEquals("And@"+ and.hashCode() +" in1 : "+i.description()+" in2 : " + i1.description(), and.description());
    }

    @Test
    public void testGetEtatSansException() throws NonConnecteException{
        //getEtat de l'interrupteur
        Interrupteur i = new Interrupteur();
        i.on();
        assertEquals(true, i.getEtat());
        i.off();
        assertEquals(false, i.getEtat());

        //getEtat de la Vanne
        Vanne v = new Vanne();
        v.setIn(i);
        assertEquals(false, v.getEtat());
        i.on();
        assertEquals(true, v.getEtat());

        //getEtat du NOT
        Not n = new Not();
        n.setIn(i);
        assertEquals(false, n.getEtat());
        i.off();
        assertEquals(true, n.getEtat());

        //getEtat du AND
        And a = new And();
        Interrupteur i1 = new Interrupteur();
        a.setIn1(i);
        a.setIn2(i1);
        i.on();
        i1.on();
        assertEquals(true, a.getEtat());
        i.off();
        assertEquals(false, a.getEtat());
        i1.off();
        assertEquals(false, a.getEtat());

        //getEtat du OR
        Or o = new Or();
        o.setIn1(i);
        o.setIn2(i1);
        i.on();
        i1.on();
        assertEquals(true, o.getEtat());
        i.off();
        assertEquals(true, o.getEtat());
        i1.off();
        assertEquals(false, o.getEtat());

    }
    @Test
    public void testGetEtatAvecException() {
        Interrupteur i = new Interrupteur();

        //getEtat de la Vanne
        Vanne v = new Vanne();
        try {assertEquals(true, v.getEtat());}
        catch(NonConnecteException e) {e.printStackTrace();}

        try {assertEquals(false, v.getEtat());}
        catch(NonConnecteException e) {e.printStackTrace();}

        //getEtat du NOT
        Not n = new Not();
        try {assertEquals(true, n.getEtat());}
        catch(NonConnecteException e) {e.printStackTrace();}
        try {assertEquals(false, n.getEtat());}
        catch(NonConnecteException e) {e.printStackTrace();}

        //getEtat du AND
        And a1 = new And();
        try {assertEquals(true, a1.getEtat());}
        catch(NonConnecteException e) {e.printStackTrace();}

        a1.setIn1(i);
        try {assertEquals(true, a1.getEtat());}
        catch(NonConnecteException e) {e.printStackTrace();}

        And a2 = new And();
        a2.setIn2(i);
        try {assertEquals(true, a2.getEtat());}
        catch (NonConnecteException e) {e.printStackTrace();}

        //getEtat du OR
        Or o1 = new Or();
        try {assertEquals(true, o1.getEtat());}
        catch(NonConnecteException e) {e.printStackTrace();}

        o1.setIn1(i);
        try {assertEquals(true, o1.getEtat());}
        catch(NonConnecteException e) {e.printStackTrace();}

        Or o2 = new Or();
        o2.setIn2(i);
        try {assertEquals(true, o2.getEtat());}
        catch (NonConnecteException e) {e.printStackTrace();}
    }




}
