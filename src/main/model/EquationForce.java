package model;

import java.util.ArrayList;
import java.util.List;

public class EquationForce implements Equation {

    private double forceVal;
    private double massVal;
    private double accelerationVal;

    private List<String> variables;

    private String eqType;
    private String unknown;

    public EquationForce() {
        eqType = "Force";
        unknown = null;
        variables = new ArrayList<String>();
        variables.add("f");
        variables.add("m");
        variables.add("a");
    }

    // MODIFIES: this
    // EFFECTS: if unknown is in variables, then set this.unknown with that value and return true, else return false
    public boolean specifyUnknown(String unknown) {
        if (variables.contains(unknown)) {
            this.unknown = unknown;
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if unknown is declared, specifyValue is in variables, and specifyValue is not the unknown, then set the
    //          variable to value and return true, else return false
    public boolean addValue(String specifyValue, double value) {
        if (unknown != null && variables.contains(specifyValue) && !specifyValue.equals(unknown)) {
            if (specifyValue.equals("f")) {
                forceVal = value;
            } else if (specifyValue.equals("m")) {
                massVal = value;
            } else {
                accelerationVal = value;
            }
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: unknown is specified, other variables have values
    // MODIFIES: this
    // EFFECTS: calculates and returns the unknown variable using the known variables, assigns calculated value to
    //          unknown value
    public double calculateResult() {
        if (unknown.equals("f")) {
            forceVal = massVal * accelerationVal;
            return forceVal;
        } else if (unknown.equals("m")) {
            massVal = forceVal / accelerationVal;
            return massVal;
        } else {
            accelerationVal = forceVal / massVal;
            return accelerationVal;
        }
    }

    public String getEqType() {
        return eqType;
    }

    public String getUnknown() {
        return unknown;
    }

    public double getForceVal() {
        return forceVal;
    }

    public double getMassVal() {
        return massVal;
    }

    public double getAccelerationVal() {
        return accelerationVal;
    }

}
