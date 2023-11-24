package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

// Represents the gui that allows the user to use the physics app

// Code influenced by Java Code Junkie Swing tutorial playlist:
// https://www.youtube.com/playlist?list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U

// Graphic from: https://freevivedsmileys.wixsite.com/freevived-smileys-de
public class PhysicsWindow {

    private JFrame frame;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel centerPanel;
    private JPanel equationScrollPanel;
    private HelpWindow helpWindow;
    private NewEqWindow newEqWindow;
    private JLabel equationInfoLabel;
    private JPanel unknownsPanel;
    private JPanel inputsPanel;

    private Equations equations;
    private ArrayList<JButton> equationButtons; // list of buttons corresponding to each equation

    private int selected = 0; // represents the index, starting at 0, for the selected equation and the selected button

    private static final String JSON_STORE = "./data/equations.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: Initializes the main window
    public PhysicsWindow() {
        equations = new Equations();
        equationButtons = new ArrayList<JButton>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.initialize();
        helpWindow = new HelpWindow();
        newEqWindow = new NewEqWindow(this);
    }

    // MODIFIES: this
    // EFFECTS: Sets all panels in jframe
    private void initialize() {
        this.setFrame();
        this.setNorthPanel();
        this.setSouthPanel();
        this.setWestPanel();
        this.setCenterPanel();
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets up frame settings
    private void setFrame() {
        frame = new JFrame();
        frame.setTitle("Physics App");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout(5, 5));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: Sets the south panel
    private void setSouthPanel() {
        southPanel = new JPanel();
        // southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        southPanel.setLayout(new GridLayout(3, 2));
        southPanel.setBackground(Color.GRAY);

        southPanel.add(this.makeNewEqButton());
        southPanel.add(this.makeSaveButton());
        southPanel.add(this.makeDeleteButton());
        southPanel.add(this.makeLoadButton());
        southPanel.add(this.makeSolveButton());
        southPanel.add(this.makeHelpButton());


        frame.add(southPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    // EFFECTS: Sets the north panel
    private void setNorthPanel() {
        northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        northPanel.setBackground(Color.YELLOW);

        JLabel title = new JLabel("Physics Calculator");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 36));

        ImageIcon smiley = new ImageIcon("./data/free_smiley_cool.png");
        Image smileyImage = smiley.getImage();
        Image newSmileyImage = smileyImage.getScaledInstance(80, 80,  java.awt.Image.SCALE_SMOOTH);
        smiley = new ImageIcon(newSmileyImage);

        title.setIcon(smiley);
        title.setIconTextGap(20);
        title.setHorizontalTextPosition(SwingConstants.LEFT);

        northPanel.add(title);

        frame.add(northPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: Sets the west panel
    private void setWestPanel() {
        westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

        equationScrollPanel = new JPanel();

        equationScrollPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JScrollPane scrollPane = new JScrollPane(equationScrollPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getVerticalScrollBar().setUnitIncrement(8);

        JLabel header = new JLabel("Equation List");
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        westPanel.add(header);
        westPanel.add(scrollPane);

        frame.add(westPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: Sets the center panel
    private void setCenterPanel() {
        centerPanel = new JPanel();
        // centerPanel.setLayout(new GridLayout(2, 1));

        centerPanel.setLayout(new GridBagLayout());

        JLabel header = new JLabel("Current Equation");
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        equationInfoLabel = new JLabel();
        equationInfoLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));

        unknownsPanel = new JPanel();
        unknownsPanel.setLayout(new GridLayout(0, 1));

        inputsPanel = new JPanel();
        inputsPanel.setLayout(new GridLayout(0, 1));

        centerPanel.add(header, createGridBagConstraints(0,0));

        centerPanel.add(equationInfoLabel, createGridBagConstraints(0,1));

        centerPanel.add(unknownsPanel, createGridBagConstraints(1,1));

        centerPanel.add(inputsPanel, createGridBagConstraints(2,1));

        frame.add(centerPanel, BorderLayout.CENTER);
    }

    // EFFECTS: Creates GridBagConstraints object with specified x, y, and gaps
    private GridBagConstraints createGridBagConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        int gap = 5;
        gbc.insets = new Insets(gap, gap + 2 * gap * x, gap, gap);
        return gbc;
    }

    // MODIFIES: this
    // EFFECTS: Creates the new equation button
    private JButton makeNewEqButton() {
        JButton newEqButton = new JButton("New Equation");
        newEqButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        newEqButton.setFocusable(false);
        newEqButton.setToolTipText("Creates a new equation of a specific type");
        newEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newEqWindow.show();
            }
        });

        return newEqButton;
    }

    // MODIFIES: this
    // EFFECTS: Creates the delete equation button
    private JButton makeDeleteButton() {
        JButton deleteButton = new JButton("Delete Equation");
        deleteButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        deleteButton.setFocusable(false);
        deleteButton.setToolTipText("Deletes the currently selected equation");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelected();
            }
        });
        return deleteButton;
    }

    // MODIFIES: this
    // EFFECTS: Creates the save button
    private JButton makeSaveButton() {
        JButton saveButton = new JButton("Save Data");
        saveButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        saveButton.setToolTipText("Saves list of all current equations to file");
        saveButton.setFocusable(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveEquations();
            }
        });
        return saveButton;
    }

    // MODIFIES: this
    // EFFECTS: Creates the load button
    private JButton makeLoadButton() {
        JButton loadButton = new JButton("Load Data");
        loadButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        loadButton.setToolTipText("Loads previous list of equations to file");
        loadButton.setFocusable(false);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadEquations();
            }
        });
        return loadButton;
    }

    // MODIFIES: this
    // EFFECTS: Creates the solve button
    private JButton makeSolveButton() {
        JButton solveButton = new JButton("Solve Equation");
        solveButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        solveButton.setFocusable(false);
        solveButton.setToolTipText("Tries to solve the currently selected equation");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solveSelected();
            }
        });
        return solveButton;
    }

    // MODIFIES: this
    // EFFECTS: Creates the help button
    private JButton makeHelpButton() {
        JButton helpButton = new JButton("Help");
        helpButton.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
        helpButton.setFocusable(false);
        helpButton.setToolTipText("Click here for help");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                helpWindow.show();
            }
        });
        return helpButton;
    }

    // MODIFIES: this
    // EFFECTS: loads equations from file
    private void loadEquations() {
        try {
            equationButtons = new ArrayList<JButton>();
            equationScrollPanel.removeAll();
            equations = jsonReader.read();
            selected = 0;
            displayEquation();
            System.out.println("Loaded equations from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads buttons for each equation
    private void loadEquationButtons() {
        List<Equation> equationList = equations.getListOfEquations();
        equationButtons.clear();
        equationScrollPanel.removeAll();
        for (Equation e: equationList) {
            loadEquationButton(e);
        }
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads the button for the given equation
    private void loadEquationButton(Equation e) {
        List<Equation> equationList = equations.getListOfEquations();
        JButton eqButton = new JButton(e.getEqType() + " Equation");
        eqButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        eqButton.setPreferredSize(new Dimension(200, 50));
        if (equationList.indexOf(e) == selected) {
            eqButton.setBackground(Color.MAGENTA);
        } else {
            eqButton.setBackground(Color.GREEN);
        }
        eqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                selected = equationList.indexOf(e);
                displayEquation();
            }
        });
        equationButtons.add(eqButton);
        equationScrollPanel.add(eqButton);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads the specify unknown buttons for each variable in the currently selected equation
    private void loadUnknownButtons() {
        unknownsPanel.removeAll();
        if (equations.getListOfEquations().size() > 0) {
            for (String s: equations.getEquation(selected).getVariables().keySet()) {
                loadUnknownButton(s);
            }
        }
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads the specify unknown button for the given variable
    private void loadUnknownButton(String s) {
        JButton unknownButton = new JButton("Unknown: " + s);
        unknownButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        unknownButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                equations.getEquation(selected).specifyUnknown(s);
                displayEquationText();
            }
        });
        unknownsPanel.add(unknownButton);
    }

    // MODIFIES: this
    // EFFECTS: loads the panel of inputs for each variable in the currently selected equation
    private void loadInputsPanel() {
        inputsPanel.removeAll();
        if (equations.getListOfEquations().size() > 0) {
            for (String s: equations.getEquation(selected).getVariables().keySet()) {
                loadInput(s);
            }
        }
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads the input for the given variable with a textfield and a submission button
    private void loadInput(String s) {
        JPanel inputPanel = new JPanel();
        JTextField inputTextField = new JTextField(10);
        JButton inputButton = new JButton("Enter value for: " + s);
        inputButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                String content = inputTextField.getText();
                String input = inputTextField.getText();
                try {
                    Double valueToAdd = Double.parseDouble(input);
                    equations.getEquation(selected).addValue(s, valueToAdd);
                } catch (NumberFormatException e) {
                    // System.out.println("Please enter a Double value:");
                }
                displayEquationText();
            }
        });

        inputPanel.add(inputTextField);
        inputPanel.add(inputButton);
        inputsPanel.add(inputPanel);
    }

    // MODIFIES: this
    // EFFECTS: makes equation of given input type
    public void makeEquation(String input) {
        if (input.equals("Force")) {
            newEquationForce();
        } else if (input.equals("Density")) {
            newEquationDensity();
        } else if (input.equals("Flow Rate")) {
            newEquationFlowRate();
        }
        loadEquationButtons();
        selected = equations.getListOfEquations().size() - 1;
        displayEquation();
    }

    // MODIFIES: this
    // EFFECTS: creates new force equation
    private void newEquationForce() {
        Equation newForceEq = new EquationForce();
        equations.addNewEquation(newForceEq);
    }

    // MODIFIES: this
    // EFFECTS: creates new density equation
    private void newEquationDensity() {
        Equation newDensityEq = new EquationDensity();
        equations.addNewEquation(newDensityEq);
    }

    // MODIFIES: this
    // EFFECTS: creates new density equation
    private void newEquationFlowRate() {
        Equation newFlowRateEq = new EquationFlowRate();
        equations.addNewEquation(newFlowRateEq);
    }

    // MODIFIES: this
    // EFFECTS: if there is at least 1 equation, deletes selected equation, changes selected value
    private void deleteSelected() {
        List<Equation> equationList = equations.getListOfEquations();
        if (equationList.size() > 0) {
            equationList.remove(selected);
            equationButtons.remove(selected);
            selected = 0;
            loadEquationButtons();
            if (equationList.size() > 0) {
                displayEquation();
            } else {
                equationInfoLabel.setText("");
                loadUnknownButtons();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: attempts to solve selected equation with given data
    private void solveSelected() {
        List<Equation> equationList = equations.getListOfEquations();
        if (equationList.size() > 0) {
            Equation currentEq = (equations.getEquation(selected));
            if (currentEq.readyToSolve()) {
                currentEq.calculateResult();
            } else {
                // System.out.println("Could not solve equation - make sure that all variables except unknown variable"
                //         + " are specified.");
            }
            displayEquation();
        }
    }

    // MODIFIES: this
    // EFFECTS: given the selected index, displays the currently selected equation in the main panel
    private void displayEquation() {
        loadEquationButtons();
        displayEquationText();
        loadUnknownButtons();
        loadInputsPanel();
    }

    // MODIFIES: this
    // EFFECTS: displays the current values of the selected equation
    private void displayEquationText() {
        if (equations.getListOfEquations().size() > 0) {
            Equation eqSelected = equations.getEquation(selected);

            String equationInfo = "<html><body>";
            HashMap<String, Double> variables = eqSelected.getVariables();

            equationInfo += "Equation Type" + ": " + eqSelected.getEqType() + "<br>";

            for (String s: variables.keySet()) {
                equationInfo += s + ": " + eqSelected.getValue(s) + "<br>";
            }

            equationInfo += "</body></html>";

            equationInfoLabel.setText(equationInfo);
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

}

