package model;

import java.util.ArrayList;
import java.util.List;

// Represents the equation for density
public class EquationDensity extends Equation {

    // EFFECTS: initializes constructor, setting eqType to "Density" and adding respective variables to hashmap
    public EquationDensity() {
        super();
        eqType = "Density";
        variables.put("d", null);
        variables.put("m", null);
        variables.put("v", null);
    }

    // REQUIRES: unknown is specified, other variables have values
    // MODIFIES: this
    // EFFECTS: calculates and returns the unknown variable using the known variables, assigns calculated value to
    //          unknown value
    @Override
    public Double calculateResult() {

        Double result;

        if (unknown.equals("d")) {
            result = variables.get("m") / variables.get("v");
            variables.put("d", result);
        } else if (unknown.equals("m")) {
            result = variables.get("v") * variables.get("d");
            variables.put("m", result);
        } else {
            result = variables.get("m") / variables.get("d");
            variables.put("v", result);
        }
        return result;
    }

    // EFFECTS: displays equation information as a String
    public String displayEquationState() {
        return "Equation Type: " + eqType + "\n"
                +
                "Formula: d = m/v\n"
                +
                "d : " + getValue("d") + "\n"
                +
                "m : " + getValue("m") + "\n"
                +
                "v : " + getValue("v");
    }

}
