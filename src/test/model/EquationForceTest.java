package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquationForceTest {

    Equation forceEq;

    @BeforeEach
    void runBefore() {
        forceEq = new EquationForce();
    }

    @Test
    void testConstructor() {
        assertEquals("Force", forceEq.getEqType());
        assertEquals(null, forceEq.getUnknown());
    }

    @Test
    void testSpecifyUnknownFail() {
        assertFalse(forceEq.specifyUnknown("b"));
    }

    @Test
    void testSpecifyUnknownSuccess() {
        assertTrue(forceEq.specifyUnknown("f"));
        assertEquals("f", forceEq.getUnknown());
    }

    @Test
    void testSpecifyUnknownMultipleTimes() {
        assertTrue(forceEq.specifyUnknown("f"));
        assertTrue(forceEq.specifyUnknown("m"));
        assertEquals("m", forceEq.getUnknown());
    }

    @Test
    void testAddValueFailInvalidValue() {
        forceEq.specifyUnknown("f");
        assertFalse(forceEq.addValue("t", 100));
        assertFalse(forceEq.addValue("f", 100));
    }

    @Test
    void testAddValuePass() {
        forceEq.specifyUnknown("f");
        assertTrue(forceEq.addValue("m", 100));
        assertEquals(100, forceEq.getVariables().get("m"));
    }

    @Test
    void testAddValueMultipleTimes() {
        forceEq.specifyUnknown("f");
        assertTrue(forceEq.addValue("m", 100));
        assertTrue(forceEq.addValue("a", 5));
        assertEquals(100, forceEq.getVariables().get("m"));
        assertEquals(5, forceEq.getVariables().get("a"));
    }

    @Test
    void testCalculateResult() {
        forceEq.specifyUnknown("f");
        forceEq.addValue("m", 50);
        forceEq.addValue("a", 10);
        assertEquals(500, forceEq.calculateResult());
    }

    @Test
    void testCalculateAndChangeValue() {
        forceEq.specifyUnknown("f");
        forceEq.addValue("m", 50);
        forceEq.addValue("a", 10);
        assertEquals(500, forceEq.calculateResult());
        forceEq.addValue("a", 20);
        assertEquals(1000, forceEq.calculateResult());
    }

    @Test
    void testCalculateResultDecimals() {
        forceEq.specifyUnknown("f");
        forceEq.addValue("m", 51.37);
        forceEq.addValue("a", 14.21);
        assertEquals(729.9677, forceEq.calculateResult());
    }

    @Test
    void testCalculateResultMass() {
        forceEq.specifyUnknown("m");
        forceEq.addValue("f", 200);
        forceEq.addValue("a", 100);
        assertEquals(2, forceEq.calculateResult());
    }

    @Test
    void testCalculateResultMassMultiple() {
        forceEq.specifyUnknown("m");
        forceEq.addValue("f", 200);
        forceEq.addValue("a", 80);
        assertEquals(2.5, forceEq.calculateResult());
        forceEq.addValue("f", 160);
        assertEquals(2, forceEq.calculateResult());
    }

    @Test
    void testCalculateResultAcceleration() {
        forceEq.specifyUnknown("a");
        forceEq.addValue("f", 200);
        forceEq.addValue("m", 100);
        assertEquals(2, forceEq.calculateResult());
    }

    @Test
    void testCalculateResultAccelerationMultiple() {
        forceEq.specifyUnknown("a");
        forceEq.addValue("f", 200);
        forceEq.addValue("m", 80);
        assertEquals(2.5, forceEq.calculateResult());
        forceEq.addValue("f", 160);
        assertEquals(2, forceEq.calculateResult());
    }

    @Test
    void testDisplayEquationStateEmpty() {
        String expected = "Equation Type: Force\n" +
                "Formula: f = m*a\n" +
                "f : no input\n" +
                "m : no input\n" +
                "a : no input";
        assertEquals(expected, forceEq.displayEquationState());
    }

    @Test
    void testDisplayEquationStateVarying() {
        forceEq.specifyUnknown("f");
        forceEq.addValue("m", 100);
        String expected = "Equation Type: Force\n" +
                "Formula: f = m*a\n" +
                "f : unknown\n" +
                "m : 100.0\n" +
                "a : no input";
        assertEquals(expected, forceEq.displayEquationState());
    }

    @Test
    void testDisplayEquationStateSolved() {
        forceEq.specifyUnknown("f");
        forceEq.addValue("m", 100);
        forceEq.addValue("a", 5);
        forceEq.calculateResult();
        String expected = "Equation Type: Force\n" +
                "Formula: f = m*a\n" +
                "f : 500.0\n" +
                "m : 100.0\n" +
                "a : 5.0";
        assertEquals(expected, forceEq.displayEquationState());
    }
}
