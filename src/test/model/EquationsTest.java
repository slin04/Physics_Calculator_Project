package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EquationsTest {
    Equations equations;

    @BeforeEach
    void runBefore() {
        equations = new Equations();
    }

    @Test
    void testNoEquations() {
        List<Equation> listOfEquations = equations.getListOfEquations();
        assertEquals(0, listOfEquations.size());
    }

    @Test
    void testOneEquations() {
        Equation forceEquation1 = new EquationForce();
        equations.addNewEquation(forceEquation1);
        List<Equation> listOfEquations = equations.getListOfEquations();
        assertEquals(1, listOfEquations.size());
        assertEquals(forceEquation1, listOfEquations.get(0));
    }

    @Test
    void testTwoEquations() {
        Equation forceEquation1 = new EquationForce();
        equations.addNewEquation(forceEquation1);
        Equation forceEquation2 = new EquationForce();
        equations.addNewEquation(forceEquation2);
        List<Equation> listOfEquations = equations.getListOfEquations();
        assertEquals(2, listOfEquations.size());
        assertEquals(forceEquation1, listOfEquations.get(0));
        assertEquals(forceEquation2, listOfEquations.get(1));
    }

    @Test
    void testAddAndRemoveOneEquation() {
        Equation forceEquation1 = new EquationForce();
        equations.addNewEquation(forceEquation1);

        assertFalse(equations.removeEquation(1));
        List<Equation> listOfEquations = equations.getListOfEquations();
        assertEquals(1, listOfEquations.size());
        assertEquals(forceEquation1, listOfEquations.get(0));

        assertTrue(equations.removeEquation(0));
        listOfEquations = equations.getListOfEquations();
        assertEquals(0, listOfEquations.size());
    }

    @Test
    void testDisplayEquations() {
        Equation forceEquation1 = new EquationForce();
        forceEquation1.specifyUnknown("f");
        forceEquation1.addValue("m", 35);

        Equation forceEquation2 = new EquationForce();
        forceEquation1.specifyUnknown("a");
        forceEquation1.addValue("f", 253);

        equations.addNewEquation(forceEquation1);
        equations.addNewEquation(forceEquation2);

        String expected = "\n" +
                "Equation Type: Force\n" +
                "Formula: f = m*a\n" +
                "f : 253.0\n" +
                "m : 35.0\n" +
                "a : unknown\n" +
                "\n" +
                "Equation Type: Force\n" +
                "Formula: f = m*a\n" +
                "f : no input\n" +
                "m : no input\n" +
                "a : no input\n";

        assertEquals(expected, equations.displayEquations());
    }
}
