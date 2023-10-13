package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EquationFlowRateTest {

    Equation flowRateEq;

    @BeforeEach
    void runBefore() {
        flowRateEq = new EquationFlowRate();
    }

    @Test
    void testConstructor() {
        assertEquals("Flow Rate", flowRateEq.getEqType());
        assertEquals(null, flowRateEq.getUnknown());
    }

    @Test
    void testSpecifyUnknownFail() {
        assertFalse(flowRateEq.specifyUnknown("c"));
    }

    @Test
    void testSpecifyUnknownSuccess() {
        assertTrue(flowRateEq.specifyUnknown("v1"));
        assertEquals("v1", flowRateEq.getUnknown());
    }

    @Test
    void testSpecifyUnknownMultipleTimes() {
        assertTrue(flowRateEq.specifyUnknown("v1"));
        assertTrue(flowRateEq.specifyUnknown("v2"));
        assertEquals("v2", flowRateEq.getUnknown());
    }

    @Test
    void testAddValueFailInvalidValue() {
        flowRateEq.specifyUnknown("v1");
        assertFalse(flowRateEq.addValue("r", 100));
        assertFalse(flowRateEq.addValue("v1", 100));
    }

    @Test
    void testAddValuePass() {
        flowRateEq.specifyUnknown("v1");
        assertTrue(flowRateEq.addValue("a1", 100));
        assertEquals(100, flowRateEq.getVariables().get("a1"));
    }

    @Test
    void testAddValueMultipleTimes() {
        flowRateEq.specifyUnknown("v1");
        assertTrue(flowRateEq.addValue("a1", 100));
        assertTrue(flowRateEq.addValue("a2", 5));
        assertEquals(100, flowRateEq.getVariables().get("a1"));
        assertEquals(5, flowRateEq.getVariables().get("a2"));
    }

    @Test
    void testCalculateResultVelOne() {
        flowRateEq.specifyUnknown("v1");
        flowRateEq.addValue("a1", 10);
        flowRateEq.addValue("v2", 25);
        flowRateEq.addValue("a2", 2);
        assertEquals(5, flowRateEq.calculateResult());
    }

    @Test
    void testCalculateResultAreaOne() {
        flowRateEq.specifyUnknown("a1");
        flowRateEq.addValue("v1", 5);
        flowRateEq.addValue("v2", 25);
        flowRateEq.addValue("a2", 2);
        assertEquals(10, flowRateEq.calculateResult());
    }

    @Test
    void testCalculateResultVelTwo() {
        flowRateEq.specifyUnknown("v2");
        flowRateEq.addValue("v1", 5);
        flowRateEq.addValue("a1", 10);
        flowRateEq.addValue("a2", 2);
        assertEquals(25, flowRateEq.calculateResult());
    }

    @Test
    void testCalculateResultAreaTwo() {
        flowRateEq.specifyUnknown("a2");
        flowRateEq.addValue("v1", 5);
        flowRateEq.addValue("a1", 10);
        flowRateEq.addValue("v2", 25);
        assertEquals(2, flowRateEq.calculateResult());
    }



    @Test
    void testDisplayEquationStateEmpty() {
        String expected = "Equation Type: Flow Rate\n" +
                "Formula: v1*a1 = v2*a2\n" +
                "v1 : no input\n" +
                "a1 : no input\n" +
                "v2 : no input\n" +
                "a2 : no input";
        assertEquals(expected, flowRateEq.displayEquationState());
    }

    @Test
    void testDisplayEquationStateVarying() {
        flowRateEq.specifyUnknown("v1");
        flowRateEq.addValue("a1", 100);
        String expected = "Equation Type: Flow Rate\n" +
                "Formula: v1*a1 = v2*a2\n" +
                "v1 : unknown\n" +
                "a1 : 100.0\n" +
                "v2 : no input\n" +
                "a2 : no input";
        assertEquals(expected, flowRateEq.displayEquationState());
    }
}
