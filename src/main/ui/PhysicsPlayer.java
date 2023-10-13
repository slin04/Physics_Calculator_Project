package ui;

import model.*;
import java.util.Scanner;

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

    public void processInput(String input) {
        if (input.equals("help")) {
            System.out.println("help, new eq, select, view all");
        } else if (input.equals("new eq")) {
            newEquation();
        } else if (input.equals("select")) {
            if (equations.getListOfEquations().size() == 0) {
                System.out.println("No equations made yet! Use \"new eq\" to make one!");
            } else {
                select();
            }
        } else if (input.equals("view all")) {
            System.out.println(viewAll());
        } else {
            System.out.println("Please enter a valid command. Enter \"help\" to view commands!");
        }
    }

    // MODIFIES: this
    public void newEquation() {
        System.out.println("Enter Equation Type: ");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            if (input.equals("Force")) {
                Equation newForceEq = new EquationForce();
                equations.addNewEquation(newForceEq);
                selected = equations.getListOfEquations().size();
                System.out.println("Made Force Equation! Force Equation is selected!");
                break;
            } else {
                System.out.println("Please enter a valid equation type:");
            }
        }
    }

    public String viewAll() {
        if (equations.getListOfEquations().size() == 0) {
            return "You have no equations right now! Use \"new eq\" to make one!";
        } else {
            return "Here are all equations made so far:\n\n" + equations.displayEquations();
        }
    }

    // make sure non-num values get fixed (ex. "ten")

    public void select() {
        System.out.println("Enter the number of the equation you want to select: ");
        while (true) {
            Scanner userInput = new Scanner(System.in);
            String input = userInput.nextLine();
            int selectValue = Integer.parseInt(input);
            int listSize = equations.getListOfEquations().size();
            if (0 < selectValue && selectValue <= listSize) {
                selected = Integer.parseInt(input);
                System.out.println("Equation " + input + " is selected!");
                break;
            } else {
                System.out.println("Please enter a valid number to be selected.");
                System.out.println("Valid numbers range from 1-" + Integer.toString(listSize));
            }
        }
    }
}
