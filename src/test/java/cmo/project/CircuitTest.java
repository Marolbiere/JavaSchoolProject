package cmo.project;

import cmo.project.io.Interrupteur;
import cmo.project.io.Vanne;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CircuitTest {
    @Test
    public void testConstruct() {
        Composant[] tabComposant = new Composant[2];
        Interrupteur i = new Interrupteur();
        Vanne v = new Vanne();
        tabComposant[0] = i;
        tabComposant[1] = v;

        Circuit circ = new Circuit("circuit 1", tabComposant);

        assertEquals("circuit 1", circ.getNom());
        assertEquals("["+i.getId()+", "+v.getId()+"]", Arrays.deepToString(circ.nomenclature().toArray(new String[0])));
    }
    @Test
    public void testGetOutputs() {
        Composant[] tabComposant = new Composant[2];
        Interrupteur i = new Interrupteur();
        Vanne v = new Vanne();
        v.setIn(i);
        tabComposant[0] = i;
        tabComposant[1] = v;

        Circuit circ = new Circuit("circuit 1", tabComposant);
        List<Vanne> a = new ArrayList<>();
        a.add(v);
        assertEquals(a, circ.getOutputs());

    }
    @Test
    public void testGetInputs() {
        Composant[] tabComposant = new Composant[2];
        Interrupteur i = new Interrupteur();
        Vanne v = new Vanne();
        v.setIn(i);
        tabComposant[0] = i;
        tabComposant[1] = v;

        Circuit circ = new Circuit("circuit 1", tabComposant);
        List<Interrupteur> a = new ArrayList<>();
        a.add(i);
        assertEquals(a, circ.getInputs());

    }

    @Test
    public void testEvaluate() {
        Composant[] tabComposant = new Composant[2];
        Interrupteur i = new Interrupteur();
        Vanne v = new Vanne();
        v.setIn(i);
        tabComposant[0] = i;
        tabComposant[1] = v;

        Circuit circ = new Circuit("circuit 1", tabComposant);

        i.on();
        assertEquals(true, circ.evaluate().value());

        i.off();
        assertEquals(false, circ.evaluate().value());

    }
}
