package model;

import java.util.ArrayList;
import java.util.List;

public class EquationForce extends Equation {

    protected EquationForce() {
        super();
        eqType = "Force";
        variables.put("f", null);
        variables.put("m", null);
        variables.put("a", null);
    }

    // REQUIRES: unknown is specified, other variables have values
    // MODIFIES: this
    // EFFECTS: calculates and returns the unknown variable using the known variables, assigns calculated value to
    //          unknown value
    @Override
    public Double calculateResult() {

        Double result;

        if (unknown.equals("f")) {
            result = variables.get("m") * variables.get("a");
            variables.put("f", result);
        } else if (unknown.equals("m")) {
            result = variables.get("f") / variables.get("a");
            variables.put("m", result);
        } else {
            result = variables.get("f") / variables.get("m");
            variables.put("a", result);
        }
        return result;
    }

    // EFFECTS: displays equation information as a String
    public String displayEquationState() {
        return "Equation Type: Force\n"
                +
                "Formula: f = m*a\n"
                +
                "f : " + getValue("f") + "\n"
                +
                "m : " + getValue("m") + "\n"
                +
                "a : " + getValue("a");
    }

    // EFFECTS: returns value of desired variable in string format depending on if it is unknown or uninitialized
    private String getValue(String var) {
        if (var.equals(unknown)) {
            return "unknown";
        } else if (variables.get(var) == null) {
            return "no input";
        } else {
            return String.valueOf(variables.get(var));
        }
    }

}
