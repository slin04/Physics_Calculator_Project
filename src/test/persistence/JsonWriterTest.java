package persistence;

import model.Equation;
import model.EquationDensity;
import model.EquationForce;
import model.Equations;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code influenced by the JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Equations eq = new Equations();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            System.out.println("Unable to write - illegal file name!");
        }
    }

    @Test
    void testWriterEmptyEquations() {
        try {
            Equations eq = new Equations();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyEquations.json");
            writer.open();
            writer.write(eq);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyEquations.json");
            eq = reader.read();
            assertEquals(0, eq.getListOfEquations().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralEquations() {
        try {
            Equations eq = new Equations();

            Equation eq1 = new EquationForce();
            eq.addNewEquation(eq1);

            Equation eq2 = new EquationDensity();
            eq2.specifyUnknown("d");
            eq2.addValue("m", 10);
            eq.addNewEquation(eq2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralEquations.json");
            writer.open();
            writer.write(eq);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralEquations.json");
            eq = reader.read();
            assertEquals(2, eq.getListOfEquations().size());

            eq1 = eq.getEquation(0);
            HashMap<String, Double> variables1 = makeVariablesForce(null, null, null);
            checkEquation(eq1, null, "Force", variables1);

            eq2 = eq.getEquation(1);
            HashMap<String, Double> variables2 = makeVariablesDensity(null, 10.0, null);
            checkEquation(eq2, "d", "Density", variables2);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
