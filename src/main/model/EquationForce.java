package model;

public class EquationForce implements Equation {

    private double forceVal;
    private double massVal;
    private double accelerationVal;

    private String unknown;
    private String eqType;

    // MODIFIES: this
    // EFFECTS: if unknown is "f", "m", or "g", then set this.unknown with that value and return true, else return false
    public boolean specifyUnknown(String unknown) {
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if specifyValue represents a value in the equation and is not the unknown, then set the variable
    //          to value
    public void addValue(String specifyValue, double value) {

    }

    // REQUIRES: unknown is specified, other variables have values
    // MODIFIES: this
    // EFFECTS: calculates and returns the unknown varaible using the known variables
    public double getResult() {
        return 0;
    }

    public String getEqType() {
        return eqType;
    }
}
