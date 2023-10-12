package model;

import java.util.HashMap;

// Represents an equation
public abstract class Equation {

    protected HashMap<String, Double> variables = new HashMap<String, Double>();

    protected String unknown;
    protected String eqType;

    protected Equation() {
        unknown = null;
    }

    // MODIFIES: this
    // EFFECTS: if unknown is in variables, then set this.unknown with that value and return true, else return false
    protected boolean specifyUnknown(String unknown) {
        if (variables.containsKey(unknown)) {
            this.unknown = unknown;
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: if specifyValue is in variables, and specifyValue is not the unknown, then set the
    //          variable to value and return true, else return false
    protected boolean addValue(String specifyValue, double value) {
        if (variables.containsKey(specifyValue) && !specifyValue.equals(unknown)) {
            variables.put(specifyValue, value);
            return true;
        } else {
            return false;
        }
    }

    abstract Double calculateResult();

    abstract String displayEquationState();

    protected HashMap<String, Double> getVariables() {
        return variables;
    }

    protected String getEqType() {
        return eqType;
    }

    protected String getUnknown() {
        return unknown;
    }
}
