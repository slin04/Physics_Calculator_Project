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
}
