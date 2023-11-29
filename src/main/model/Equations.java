package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents a list of equations that can be manipulated in various ways
public class Equations implements Writable {
    private List<Equation> equationList = new ArrayList<Equation>();

    // MODIFIES: this
    // EFFECTS: adds equation to equationList
    public void addNewEquation(Equation equation) {
        equationList.add(equation);
        EventLog.getInstance().logEvent(new Event("Added equation of type " + equation.getEqType()
                + " to equations list!"));
    }

    // REQUIRES: equationIndex is non-negative and size of equationList is greater than equationIndex
    // MODIFIES: this
    // EFFECTS: equationIndex represents the index of the position that is to be removed, starting at 0.
    //          remove the equation from equationList
    public void removeEquation(int equationIndex) {
        equationList.remove(equationIndex);
        EventLog.getInstance().logEvent(new Event("Removed equation at index: "
                + String.valueOf(equationIndex)));
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

    @Override
    // EFFECTS: Converts equations to JSONObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Equations", equationListToJson());
        return json;
    }

    // EFFECTS: returns equationList as a JSON array
    private JSONArray equationListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Equation e: equationList) {
            jsonArray.put(e.toJson());
        }

        return jsonArray;
    }

}
