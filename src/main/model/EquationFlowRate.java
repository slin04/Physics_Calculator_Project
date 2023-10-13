package model;

import java.util.ArrayList;
import java.util.List;

// Represents the equation for force
public class EquationFlowRate extends Equation {

    public EquationFlowRate() {
        super();
        eqType = "Flow Rate";
        variables.put("v1", null);
        variables.put("v2", null);
        variables.put("a1", null);
        variables.put("a2", null);
    }

    // REQUIRES: unknown is specified, other variables have values
    // MODIFIES: this
    // EFFECTS: calculates and returns the unknown variable using the known variables, assigns calculated value to
    //          unknown value
    @Override
    public Double calculateResult() {

        Double result;

        if (unknown.equals("v1")) {
            result = variables.get("v2") * (variables.get("a2") / (variables.get("a1")));
            variables.put("v1", result);
        } else if (unknown.equals("a1")) {
            result = variables.get("a2") * (variables.get("v2") / (variables.get("v1")));
            variables.put("a1", result);
        } else if (unknown.equals("v2")) {
            result = variables.get("v1") * (variables.get("a1") / (variables.get("a2")));
            variables.put("v2", result);
        } else {
            result = variables.get("a1") * (variables.get("v1") / (variables.get("v2")));
            variables.put("a2", result);
        }
        return result;
    }

    // EFFECTS: displays equation information as a String
    @Override
    public String displayEquationState() {
        return "Equation Type: " + eqType + "\n"
                +
                "Formula: v1*a1 = v2*a2\n"
                +
                "v1 : " + getValue("v1") + "\n"
                +
                "a1 : " + getValue("a1") + "\n"
                +
                "v2 : " + getValue("v2")  + "\n"
                +
                "a2 : " + getValue("a2");
    }
}

