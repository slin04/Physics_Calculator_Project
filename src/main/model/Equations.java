package model;

import java.util.*;

public class Equations {
    private List<Equation> equationList = new ArrayList<Equation>();

    // MODIFIES: this
    // EFFECTS: adds equation to equationList
    public void addNewEquation(Equation equation) {
        equationList.add(equation);
    }

    // REQUIRES: equationIndex is non-negative
    // MODIFIES: this
    // EFFECTS: equationIndex represents the index of the position that is to be removed, starting at 0.
    //          if an equation exists at the index, remove the equation and return true, else return false
    public boolean removeEquation(int equationIndex) {
        if (equationList.size() > equationIndex) {
            equationList.remove(equationIndex);
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: displays all equations on list by calling displayEquationState method on every equation
    public String displayEquations() {
        String display = "";
        for (Equation eq: equationList) {
            display += eq.displayEquationState() + "\n\n";
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
