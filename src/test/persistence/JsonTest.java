package persistence;

import model.Equation;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Code influenced by the JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    // EFFECTS: Checks if equation has same unknown, eqType, and variables as those entered
    protected void checkEquation(Equation eq, String unknown, String eqType, HashMap<String, Double> variables) {
        assertEquals(unknown, eq.getUnknown());
        assertEquals(eqType, eq.getEqType());
        compareVariables(eq.getVariables(), variables);
    }

    // EFFECTS: Compares two HashMaps of variables
    private void compareVariables(HashMap<String, Double> variables1, HashMap<String, Double> variables2) {
        for (String key : variables1.keySet()) {
            assertEquals(variables1.get(key), (variables2.get(key)));
        }
    }

    // EFFECTS: Makes HashMap of Force variables
    protected HashMap<String, Double> makeVariablesForce(Double f, Double m, Double a) {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("f", f);
        variables.put("m", m);
        variables.put("a", a);
        return variables;
    }

    // EFFECTS: Makes HashMap of Density variables
    protected HashMap<String, Double> makeVariablesDensity(Double d, Double m, Double v) {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("d", d);
        variables.put("m", m);
        variables.put("v", v);
        return variables;
    }

    // EFFECTS: Makes HashMap of Flow Rate variables
    protected HashMap<String, Double> makeVariablesFlowRate(Double v1, Double v2, Double a1, Double a2) {
        HashMap<String, Double> variables = new HashMap<>();
        variables.put("v1", v1);
        variables.put("v2", v2);
        variables.put("a1", a1);
        variables.put("a2", a2);
        return variables;
    }
}
