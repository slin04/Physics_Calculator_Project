package model;

// Represents an equation
public interface Equation {

    boolean specifyUnknown(String unknown);

    void addValue(String specifyValue, double value);

    double getResult();

}
