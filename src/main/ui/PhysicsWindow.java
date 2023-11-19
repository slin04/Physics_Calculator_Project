package ui;

import model.Equation;
import model.Equations;
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

// Represents the gui that allows the user to use the physics app

// Code influenced by Java Code Junkie Swing tutorial playlist:
// https://www.youtube.com/playlist?list=PL3bGLnkkGnuV699lP_f9DvxyK5lMFpq6U

// Graphic from: https://freevivedsmileys.wixsite.com/freevived-smileys-de
public class PhysicsWindow {

    private JFrame frame;
    private JPanel southPanel;
    private JPanel northPanel;
    private JPanel westPanel;
    private JPanel equationScrollPanel;
    private HelpWindow helpWindow;

    private Equations equations;
    private ArrayList<JButton> equationButtons; // list of buttons corresponding to each equation

    private int selected = 0; // represents the index, starting at 0, for the selected equation and the selected button

    private static final String JSON_STORE = "./data/equations.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public PhysicsWindow() {
        equations = new Equations();
        equationButtons = new ArrayList<JButton>();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.initialize();
        helpWindow = new HelpWindow();
    }

    // initialize with JFrame settings
    private void initialize() {
        this.setFrame();
        this.setNorthPanel();
        this.setSouthPanel();
        this.setWestPanel();
        frame.setVisible(true);
    }

    private void setFrame() {
        frame = new JFrame();
        frame.setTitle("Physics App");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout(5, 5));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

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

    private void setWestPanel() {
        westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

        equationScrollPanel = new JPanel();

        equationScrollPanel.setLayout(new GridLayout(0, 1, 10, 10));

//        for (int i = 0; i < 20; i++) {
//            JButton testButton = new JButton("Equation" + String.valueOf(i));
//            testButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
//            testButton.setPreferredSize(new Dimension(200, 50));
//            equationScrollPanel.add(testButton);
//        }

        JScrollPane scrollPane = new JScrollPane(equationScrollPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.getVerticalScrollBar().setUnitIncrement(8);

        JLabel header = new JLabel("Equation List");
        header.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        westPanel.add(header);
        westPanel.add(scrollPane);

        frame.add(westPanel, BorderLayout.WEST);
    }

    private JButton makeNewEqButton() {
        JButton newEqButton = new JButton("New Equation");
        newEqButton.setFocusable(false);
        newEqButton.setToolTipText("Creates a new equation of a specific type");
        newEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This should create a new equation!");
            }
        });

        return newEqButton;
    }

    private JButton makeDeleteButton() {
        JButton deleteButton = new JButton("Delete Equation");
        deleteButton.setFocusable(false);
        deleteButton.setToolTipText("Deletes the currently selected equation");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This should delete the selected equation!");
            }
        });
        return deleteButton;
    }

    private JButton makeSaveButton() {
        JButton saveButton = new JButton("Save Data");
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

    private JButton makeLoadButton() {
        JButton loadButton = new JButton("Load Data");
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

    private JButton makeSolveButton() {
        JButton solveButton = new JButton("Solve Equation");
        solveButton.setFocusable(false);
        solveButton.setToolTipText("Tries to solve the currently selected equation");
        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This should try to solve the selected equation!");
            }
        });
        return solveButton;
    }

    private JButton makeHelpButton() {
        JButton helpButton = new JButton("Help");
        helpButton.setFocusable(false);
        helpButton.setToolTipText("Click here for help");
        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This should display a helpful popup");
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
            loadButtons();
            System.out.println("Loaded equations from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: produces buttons for each equation
    private void loadButtons() {
        List<Equation> equationList = equations.getListOfEquations();
        for (Equation e: equationList) {
            JButton eqButton = new JButton(e.getEqType());
            eqButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
            eqButton.setPreferredSize(new Dimension(200, 50));
            eqButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a) {
                    selected = equationList.indexOf(e);
                    displayEquation(selected);
                }
            });
            equationButtons.add(eqButton);
            equationScrollPanel.add(eqButton);
            frame.setVisible(true);
        }
    }

    // MODIFES: this
    // EFFECTS: given the selected index, displays the currently selected equation in the main panel
    private void displayEquation(int selected) {
        System.out.println(equations.getEquation(selected).displayEquationState() + "\n");
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

