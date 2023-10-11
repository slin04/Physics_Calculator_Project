package model;

// Represents an equation
public interface Equation {

    boolean specifyUnknown(String unknown);

    boolean addValue(String specifyValue, double value);

    double calculateResult();

    String getEqType();

    String getUnknown();
}
