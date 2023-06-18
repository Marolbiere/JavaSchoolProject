package cmo.project;

import cmo.project.signal.SignalBas;
import cmo.project.signal.SignalHaut;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class SignalLogicTest {
    @Test
    public void testSignalLogicValue() {
        SignalHaut t = new SignalHaut();
        SignalBas f = new SignalBas();

        assertEquals(true,t.value());
        assertEquals(false, f.value());
    }

    @Test
    public void testSignalLogicNot() {
        SignalHaut t = new SignalHaut();
        SignalBas f = new SignalBas();

        assertEquals(false , t.not().value());
        assertEquals(true, f.not().value());
    }

    @Test
    public void testSignalLogicAnd() {
        SignalHaut t = new SignalHaut();
        SignalBas f = new SignalBas();

        assertEquals(false, t.and(f).value());
        assertEquals(true, t.and(t).value());

        assertEquals(false, f.and(t).value());
        assertEquals(false, f.and(f).value());
    }

    @Test
    public void testSignalLogicOr() {
        SignalHaut t = new SignalHaut();
        SignalBas f = new SignalBas();

        assertEquals(true, t.or(f).value());
        assertEquals(true, t.or(t).value());

        assertEquals(true, f.or(t).value());
        assertEquals(false, f.or(f).value());
    }
    @Test
    public void testSignalLogicToString() {
        SignalHaut t = new SignalHaut();
        SignalBas f = new SignalBas();

        assertEquals("true", t.toString());
        assertEquals("false", f.toString());
    }
}
