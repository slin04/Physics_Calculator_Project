package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquationDensityTest {

    Equation densityEq;

    @BeforeEach
    void runBefore() {
        densityEq = new EquationDensity();
    }

    @Test
    void testConstructor() {
        assertEquals("Density", densityEq.getEqType());
        assertEquals(null, densityEq.getUnknown());
    }

    @Test
    void testSpecifyUnknownFail() {
        assertFalse(densityEq.specifyUnknown("c"));
    }

    @Test
    void testSpecifyUnknownSuccess() {
        assertTrue(densityEq.specifyUnknown("d"));
        assertEquals("d", densityEq.getUnknown());
    }

    @Test
    void testSpecifyUnknownMultipleTimes() {
        assertTrue(densityEq.specifyUnknown("d"));
        assertTrue(densityEq.specifyUnknown("m"));
        assertEquals("m", densityEq.getUnknown());
    }

    @Test
    void testAddValueFailInvalidValue() {
        densityEq.specifyUnknown("d");
        assertFalse(densityEq.addValue("y", 100));
        assertFalse(densityEq.addValue("d", 100));
    }

    @Test
    void testAddValuePass() {
        densityEq.specifyUnknown("d");
        assertTrue(densityEq.addValue("m", 100));
        assertEquals(100, densityEq.getVariables().get("m"));
    }

    @Test
    void testAddValueMultipleTimes() {
        densityEq.specifyUnknown("d");
        assertTrue(densityEq.addValue("m", 100));
        assertTrue(densityEq.addValue("v", 5));
        assertEquals(100, densityEq.getVariables().get("m"));
        assertEquals(5, densityEq.getVariables().get("v"));
    }

    @Test
    void testCalculateResult() {
        densityEq.specifyUnknown("d");
        densityEq.addValue("m", 500);
        densityEq.addValue("v", 10);
        assertEquals(50, densityEq.calculateResult());
    }

    @Test
    void testCalculateAndChangeValue() {
        densityEq.specifyUnknown("d");
        densityEq.addValue("m", 500);
        densityEq.addValue("v", 10);
        assertEquals(50, densityEq.calculateResult());
        densityEq.addValue("v", 200);
        assertEquals(2.50, densityEq.calculateResult());
    }

    @Test
    void testDisplayEquationStateEmpty() {
        String expected = "Equation Type: Density\n" +
                "Formula: d = m/v\n" +
                "d : no input\n" +
                "m : no input\n" +
                "v : no input";
        assertEquals(expected, densityEq.displayEquationState());
    }

    @Test
    void testDisplayEquationStateVarying() {
        densityEq.specifyUnknown("f");
        densityEq.addValue("m", 100);
        String expected = "Equation Type: Density\n" +
                "Formula: d = m/v\n" +
                "d : no input\n" +
                "m : 100.0\n" +
                "v : no input";
        assertEquals(expected, densityEq.displayEquationState());
    }
}
