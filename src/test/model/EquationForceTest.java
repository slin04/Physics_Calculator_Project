package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquationForceTest {

    EquationForce forceEq;

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
    void testAddValueFailUnknownUndeclared() {
        assertFalse(forceEq.addValue("f", 100));
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
        assertEquals(100, forceEq.getMassVal());
    }

    @Test
    void testAddValueMultipleTimes() {
        forceEq.specifyUnknown("f");
        assertTrue(forceEq.addValue("m", 100));
        assertTrue(forceEq.addValue("a", 5));
        assertEquals(100, forceEq.getMassVal());
        assertEquals(5, forceEq.getAccelerationVal());
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
}
