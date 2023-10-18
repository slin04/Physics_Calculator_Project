package model;

import java.util.*;

// Represents a list of equations that can be manipulated in various ways
public class Equations {
    private List<Equation> equationList = new ArrayList<Equation>();

    // MODIFIES: this
    // EFFECTS: adds equation to equationList
    public void addNewEquation(Equation equation) {
        equationList.add(equation);
    }

    // REQUIRES: equationIndex is non-negative and size of equationList is greater than equationIndex
    // MODIFIES: this
    // EFFECTS: equationIndex represents the index of the position that is to be removed, starting at 0.
    //          remove the equation from equationList
    public void removeEquation(int equationIndex) {
        equationList.remove(equationIndex);
    }

    // EFFECTS: displays all equations on list by calling displayEquationState method on every equation
    public String displayEquations() {
        String display = "";
        for (Equation eq: equationList) {
            display += "\n" + eq.displayEquationState() + "\n";
        }
        return display;
    }

    public Equation getEquation(int index) {
        return equationList.get(index);
    }

    public List<Equation> getListOfEquations() {
        return equationList;
    }

}
