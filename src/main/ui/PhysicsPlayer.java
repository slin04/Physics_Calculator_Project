package ui;

import model.*;
import java.util.Scanner;

import java.lang.NumberFormatException;

public class PhysicsPlayer {

    Equations equations;

    int selected;

    public PhysicsPlayer() {
        equations = new Equations();
        run();
    }

    public void run() {
        System.out.println("Welcome to the Physics Calculator!\nEnter \"help\" to view commands!");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            processInput(userInput.nextLine());
        }
    }

    private void processInput(String input) {
        if (input.equals("help")) {
            System.out.println("help, new eq, select, specify, enter val, view, view all");
        } else if (input.equals("new eq")) {
            newEquation();
        } else if (input.equals("select")) {
            select();
        } else if (input.equals("specify")) {
            specifyUnknown();
        } else if (input.equals("enter val")) {
            enterValue();
        } else if (input.equals("view")) {
            System.out.println(view());
        } else if (input.equals("view all")) {
            System.out.println(viewAll());
        } else {
            System.out.println("Please enter a valid command. Enter \"help\" to view commands!");
        }
    }

    // MODIFIES: this
    private void newEquation() {
        System.out.println("Enter Equation Type: ");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("Force")) {
                newEquationForce();
                break;
            } else {
                System.out.println("Please enter a valid equation type:");
            }
        }
    }

    private void newEquationForce() {
        Equation newForceEq = new EquationForce();
        equations.addNewEquation(newForceEq);
        selected = equations.getListOfEquations().size();
        System.out.println("Made Force Equation! Force Equation is selected!");
    }

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
                        selected = Integer.parseInt(input);
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

    private void enterValue() {
        if (isEmpty()) {
            System.out.println(("You have no equations right now! Use \"new eq\" to make one!"));
        } else {
            String varName;
            System.out.println("Enter variable name:");
            while (true) {
                Scanner userInput = new Scanner(System.in);
                String input = userInput.nextLine();
                // check that variable name is valid
                if (equations.getEquation(selected - 1).addValue(input, 0)) {
                    varName = input;
                    break;
                } else {
                    System.out.println("Value specified is not in equation! The variables in this equation are:");
                    System.out.println(getVariableNames());
                }
            }
            System.out.println("Enter value:");
            enterValue(varName);
        }
    }

    // REQUIRES: varName is part of the selected equation's variables
    private void enterValue(String varName) {
        double valueToAdd;
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            try {
                valueToAdd = Double.parseDouble(input);
                equations.getEquation(selected - 1).addValue(varName, valueToAdd);
                System.out.println("Successfully entered value " + String.valueOf(valueToAdd) + " for variable "
                                    + varName);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a Double value:");
            }
        }
    }

    private String getVariableNames() {
        String variables = "";
        for (String i: equations.getEquation(selected - 1).getVariables().keySet()) {
            variables += i + ", ";
        }
        variables = variables.substring(0, variables.length() - 2);
        return variables;
    }


    private String view() {
        return equations.getEquation(selected - 1).displayEquationState();
    }

    private String viewAll() {
        if (isEmpty()) {
            return "You have no equations right now! Use \"new eq\" to make one!";
        } else {
            return "Here are all equations made so far:\n" + equations.displayEquations();
        }
    }

    private boolean isEmpty() {
        return (equations.getListOfEquations().size() == 0);
    }

}
