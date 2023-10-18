package model;

import java.util.HashMap;

// Represents an equation with a hashmap with the keys representing variable names, and the values representing
// variable values
public abstract class Equation {

    protected HashMap<String, Double> variables = new HashMap<String, Double>();

    protected String unknown;
    protected String eqType;

    // EFFECTS: initializes equation with an unspecified unknown value
    protected Equation() {
        unknown = null;
    }

    // MODIFIES: this
    // EFFECTS: if unknown is in variables, then set this.unknown with that value, set the unknown value in variables to
    // null, and return true, else return false
    public boolean specifyUnknown(String unknown) {
        if (variables.containsKey(unknown)) {
            this.unknown = unknown;
            variables.put(unknown, null);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: if specifyValue is in variables, and specifyValue is not the unknown, then set the
    //          variable to value and return true, else return false
    public boolean addValue(String specifyValue, double value) {
        if (variables.containsKey(specifyValue) && !specifyValue.equals(unknown)) {
            variables.put(specifyValue, value);
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: var is in variables
    // EFFECTS: returns value of desired variable in string format depending on if it is unknown or uninitialized
    protected String getValue(String var) {
        if (var.equals(unknown)) {
            if (variables.get(unknown) == null) {
                return "unknown";
            } else {
                return String.valueOf(variables.get(unknown));
            }
        } else if (variables.get(var) == null) {
            return "no input";
        } else {
            return String.valueOf(variables.get(var));
        }
    }

    // REQUIRES: unknown is specified, other variables have values
    // MODIFIES: this
    // EFFECTS: calculates and returns the unknown variable using the known variables, assigns calculated value to
    //          unknown value
    public abstract Double calculateResult();

    // EFFECTS: displays equation information as a String
    public abstract String displayEquationState();

    public HashMap<String, Double> getVariables() {
        return variables;
    }

    public String getEqType() {
        return eqType;
    }

    public String getUnknown() {
        return unknown;
    }
}
