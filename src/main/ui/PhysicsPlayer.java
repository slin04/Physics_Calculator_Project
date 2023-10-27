package ui;

import model.*;
import org.json.JSONException;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import java.lang.NumberFormatException;


// The player that communicates between the user and the list of equations
public class PhysicsPlayer {

    private Equations equations;

    private boolean isRunning = true;
    private int selected = 1;

    private static final String JSON_STORE = "./data/equations.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Creates a new set of equations and starts the program
    public PhysicsPlayer() {
        equations = new Equations();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        run();
    }

    // EFFECTS: runs the program by taking user input which is then processed
    public void run() {
        System.out.println("Welcome to the Physics Calculator!\nEnter \"help\" to view commands!");
        while (isRunning) {
            Scanner userInput = new Scanner(System.in);
            processInput(userInput.nextLine());
        }
    }

    // EFFECTS: takes in input and runs appropriate method
    @SuppressWarnings("methodlength")
    private void processInput(String input) {
        if (input.equals("help")) {
            help();
        } else if (input.equals("new eq")) {
            newEquation();
        } else if (input.equals("del eq")) {
            deleteEquation();
        } else if (input.equals("select")) {
            select();
        } else if (input.equals("specify")) {
            specifyUnknown();
        } else if (input.equals("enter")) {
            enterValue();
        } else if (input.equals("solve")) {
            solve();
        } else if (input.equals("view")) {
            System.out.println(view());
        } else if (input.equals("view all")) {
            System.out.println(viewAll());
        } else if (input.equals("save")) {
            saveEquations();
        } else if (input.equals("load")) {
            loadEquations();
        } else if (input.equals("quit")) {
            isRunning = false;
        } else {
            System.out.println("Please enter a valid command. Enter \"help\" to view commands!");
        }
    }

    private void help() {
        System.out.println("ALL COMMANDS ARE LISTED BELOW:\n");
        System.out.println("help - displays this text");
        System.out.println("new eq - creates new equation and automatically selects newly created equation");
        System.out.println("del eq - deletes equation at user-specified index");
        System.out.println("select - selects equation at user-specified index");
        System.out.println("specify - specifies unknown value (the value to be solved for) for the selected equation");
        System.out.println("enter - enters known value for the selected equation");
        System.out.println("solve - attempts to solve selected equation with given information");
        System.out.println("view - displays status of selected equation");
        System.out.println("view all - displays status of all equations in list");
        System.out.println("save - saves equations to file");
        System.out.println("load - loads equations from file");
        System.out.println("quit - quits the application\n");
        System.out.println("NOTE: The list of equations starts with an index at 1. This means that the first equation"
                            + " added to the list will have index 1, the second equation will have index 2, etc");
    }

    // EFFECTS: prompts user to specify the type of equation to create
    private void newEquation() {
        System.out.println("Enter Equation Type: ");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("Force")) {
                newEquationForce();
                break;
            } else if (input.equals("Density")) {
                newEquationDensity();
                break;
            } else if (input.equals("Flow Rate")) {
                newEquationFlowRate();
                break;
            } else {
                System.out.println("Please enter a valid equation type:");
                System.out.println("Valid equation types are Force, Density, and Flow Rate.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new force equation
    private void newEquationForce() {
        Equation newForceEq = new EquationForce();
        equations.addNewEquation(newForceEq);
        selected = equations.getListOfEquations().size();
        System.out.println("Made Force Equation! Force Equation is selected!");
    }

    // MODIFIES: this
    // EFFECTS: creates new density equation
    private void newEquationDensity() {
        Equation newDensityEq = new EquationDensity();
        equations.addNewEquation(newDensityEq);
        selected = equations.getListOfEquations().size();
        System.out.println("Made Density Equation! Density Equation is selected!");
    }

    // MODIFIES: this
    // EFFECTS: creates new density equation
    private void newEquationFlowRate() {
        Equation newFlowRateEq = new EquationFlowRate();
        equations.addNewEquation(newFlowRateEq);
        selected = equations.getListOfEquations().size();
        System.out.println("Made Flow Rate Equation! Flow Rate Equation is selected!");
    }

    // MODIFIES: this
    // EFFECTS: if there is at least 1 equation in the list, the user is prompted to enter an index to be deleted.
    //          if a valid index is entered, the corresponding equation is deleted and selected is set to 1
    //          if not, then
    private void deleteEquation() {
        if (equations.getListOfEquations().size() == 0) {
            System.out.println("You must have at least 1 equation before you can delete any equations!");
        } else {
            System.out.println(viewAll());
            System.out.println("Enter index of equation to delete:");
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            try {
                int indexToRemove = Integer.parseInt(input);
                int listSize = equations.getListOfEquations().size();
                if (0 < indexToRemove && indexToRemove <= listSize) {
                    equations.removeEquation(indexToRemove - 1);
                    selected = 1;
                    System.out.println("Equation successfully deleted!");
                } else {
                    System.out.println("Deleting cancelled. Please enter a valid index next time!");
                    System.out.println("Valid numbers range from 1-" + Integer.toString(listSize) + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Deleting cancelled. Please enter an integer value next time!");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: if the list is not empty, the user is prompted to select the equation corresponding to an index
    private void select() {
        if (isEmpty()) {
            System.out.println("No equations made yet! Use \"new eq\" to make one!");
        } else {
            System.out.println("Enter the number of the equation you want to select: ");
            while (true) {
                Scanner userInput = new Scanner(System.in);
                String input = userInput.nextLine();
                try {
                    int selectValue = Integer.parseInt(input);
                    int listSize = equations.getListOfEquations().size();
                    if (0 < selectValue && selectValue <= listSize) {
                        selected = selectValue;
                        System.out.println("Equation " + input + " is selected!");
                        break;
                    } else {
                        System.out.println("Please enter a valid number to be selected.");
                        System.out.println("Valid numbers range from 1-" + Integer.toString(listSize) + ".");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Please enter an integer value!");
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to specify the unknown value for the currently selected equation unless list is empty
    private void specifyUnknown() {
        if (isEmpty()) {
            System.out.println(("You have no equations right now! Use \"new eq\" to make one!"));
        } else {
            System.out.println("Enter unknown value to be specified:");
            while (true) {
                Scanner userInput = new Scanner(System.in);
                String input = userInput.nextLine();
                Equation currentEq = (equations.getEquation(selected - 1));
                if (currentEq.specifyUnknown(input)) {
                    System.out.println("Unknown value successfully specified!");
                    break;
                } else {
                    System.out.println("Value specified is not in equation! The variables in this equation are:");
                    System.out.println(getVariableNames());
                }
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to enter the value for the currently selected equation unless list is empty
    private void enterValue() {
        if (isEmpty()) {
            System.out.println(("You have no equations right now! Use \"new eq\" to make one!"));
        } else {
            String varName;
            while (true) {
                System.out.println("Enter variable name:");
                Scanner userInput = new Scanner(System.in);
                String input = userInput.nextLine();
                // check that variable name is valid
                if (equations.getEquation(selected - 1).addValue(input, 0)) {
                    varName = input;
                    break;
                } else if (input.equals(equations.getEquation(selected - 1).getUnknown())) {
                    System.out.println("Variable specified cannot be entered because it is the unknown!");
                } else {
                    System.out.println("Variable specified is not in equation! The variables in this equation are:");
                    System.out.println(getVariableNames());
                }
            }
            System.out.println("Enter value:");
            enterValueWithVariable(varName);
        }
    }

    // REQUIRES: varName is part of the selected equation's variables
    // MODIFIES: this
    // EFFECTS: helper function that allows the user to specify the value added to the variable
    private void enterValueWithVariable(String varName) {
        Double valueToAdd;
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            try {
                valueToAdd = Double.parseDouble(input);
                equations.getEquation(selected - 1).addValue(varName, valueToAdd);
                System.out.println("Successfully entered value " + String.valueOf(valueToAdd) + " for variable "
                                    + varName + "!");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a Double value:");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to solve the currently selected equation unless list is empty
    private void solve() {
        if (isEmpty()) {
            System.out.println(("You have no equations right now! Use \"new eq\" to make one!"));
        } else {
            Equation currentEq = (equations.getEquation(selected - 1));
            if (currentEq.readyToSolve()) {
                Double result = currentEq.calculateResult();
                System.out.println("Equation solved successfully! Result is: " + String.valueOf(result));
            } else {
                System.out.println("Could not solve equation - make sure that all variables except unknown variable"
                                    + " are specified.");
            }
        }
    }

    // EFFECTS: returns names of all variables in the currently selected equation
    private String getVariableNames() {
        String variables = "";
        for (String i: equations.getEquation(selected - 1).getVariables().keySet()) {
            variables += i + ", ";
        }
        variables = variables.substring(0, variables.length() - 2);
        return variables;
    }


    // EFFECTS: returns the string containing the information of the currently selected equation
    private String view() {
        if (!isEmpty()) {
            return "\n" + equations.getEquation(selected - 1).displayEquationState() + "\n";
        }

        return "You have no equations right now! Use \"new eq\" to make one!";

    }

    // EFFECTS: returns the string containing information of all equations in the list, unless there are none
    private String viewAll() {
        if (isEmpty()) {
            return "You have no equations right now! Use \"new eq\" to make one!";
        } else {
            return "Here are all equations made so far:\n" + equations.displayEquations();
        }
    }

    // EFFECTS: saves the equations to file
    private void saveEquations() {
        try {
            jsonWriter.open();
            jsonWriter.write(equations);
            jsonWriter.close();
            System.out.println("Saved equations to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads equations from file
    private void loadEquations() {
        try {
            equations = jsonReader.read();
            System.out.println("Loaded equations from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: checks if the size of the list is 0
    private boolean isEmpty() {
        return (equations.getListOfEquations().size() == 0);
    }
}
