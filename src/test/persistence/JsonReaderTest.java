package persistence;

import model.Equation;
import model.Equations;
import org.json.JSONException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Code influenced by the JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Equations eqs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            System.out.println("No such file exists");
        }
    }

    @Test
    void testReaderEmptyEquations() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyEquations.json");
        try {
            Equations eqs = reader.read();
            assertEquals(0, eqs.getListOfEquations().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralEquations() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralEquations.json");
        try {
            Equations eqs = reader.read();
            List<Equation> equationList = eqs.getListOfEquations();

            HashMap<String, Double> variables1 = makeVariablesForce(null, null, null);
            checkEquation(equationList.get(0), null, "Force", variables1);

            HashMap<String, Double> variables2 = makeVariablesDensity(null, null, null);
            checkEquation(equationList.get(1), "d", "Density", variables2);

            HashMap<String, Double> variables3 = makeVariablesFlowRate(null, 10.0, null, null);
            checkEquation(equationList.get(2), "a2", "Flow Rate", variables3);

            HashMap<String, Double> variables4 = makeVariablesForce(null, 35.0, 21.0);
            checkEquation(equationList.get(3), "f", "Force", variables4);

            HashMap<String, Double> variables5 = makeVariablesForce(1288.0, 23.0, 56.0);
            checkEquation(equationList.get(4), "f", "Force", variables5);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
