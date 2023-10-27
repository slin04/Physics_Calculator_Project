package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads Equations from JSON data stored in file
// Code influenced by the JsonSerializationDemo: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Equations read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseEquations(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Equations from JSON object and returns it
    private Equations parseEquations(JSONObject jsonObject) {
        Equations equations = new Equations();
        addEquations(equations, jsonObject);
        return equations;
    }

    // MODIFIES: equations
    // EFFECTS: parses equations from JSON object and adds them to workroom
    private void addEquations(Equations equations, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Equations");
        for (Object json : jsonArray) {
            JSONObject nextEquation = (JSONObject) json;
            addEquation(equations, nextEquation);
        }
    }

    // MODIFIES: equations
    // EFFECTS: parses equation from JSON object and adds it to workroom
    private void addEquation(Equations equations, JSONObject jsonObject) {
        String equationType = jsonObject.getString("Equation Type");
        String unknown = jsonObject.getString("unknown");
        if (unknown.equals("null")) {
            unknown = null;
        }
        JSONObject variables = jsonObject.getJSONObject("variables");
        Equation equation = makeEquation(equationType, unknown, variables);
        equations.addNewEquation(equation);
    }

    // EFFECTS: returns an equation constructed based on given information
    private Equation makeEquation(String eqType, String unknown, JSONObject variables) {
        Equation equation;

        Map<String, Object> variableMap = variables.toMap();

        if (eqType.equals("Force")) {
            equation = new EquationForce();
        } else if (eqType.equals("Density")) {
            equation = new EquationDensity();
        } else {
            equation = new EquationFlowRate();
        }

        // Specify unknown if unknown is not null
        if (unknown != null) {
            equation.specifyUnknown(unknown);
        }

        addVariables(equation, variableMap);

        return equation;
    }

    // MODIFIES: equation
    // EFFECTS: adds all variables to equation
    private void addVariables(Equation equation, Map<String, Object> variableMap) {

        // When unknown is specified and the unknown value has a variable, that means that the equation must have
        // been solved before being saved, so we must solve it again while making the variables
        boolean shouldSolve = false;

        for (Map.Entry<String, Object> set : variableMap.entrySet()) {
            String name = set.getKey();
            Double value = Double.valueOf(set.getValue().toString());
            if (name.equals(equation.getUnknown()) && value != null) {
                shouldSolve = true;
            }
            equation.addValue(name, value);

        }

        if (shouldSolve) {
            equation.calculateResult();
        }
    }
}
