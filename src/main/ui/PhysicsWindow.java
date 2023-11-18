package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhysicsWindow {

    private JFrame frame;

    public PhysicsWindow() {
        this.initialize();
        this.show();
    }

    // initialize with JFrame settings
    public void initialize() {
        this.setFrame();
        this.setSouthPanel();
    }

    public void setFrame() {
        frame = new JFrame();
        frame.setTitle("Physics App");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout(5, 5));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    public void setSouthPanel() {
        JPanel panel = new JPanel();
        // panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        panel.setLayout(new GridLayout(4, 1));
        panel.setBackground(Color.GRAY);

        panel.add(this.makeNewEqButton());
        panel.add(this.makeDeleteButton());
        panel.add(this.makeSaveButton());
        panel.add(this.makeLoadButton());

        frame.add(panel, BorderLayout.SOUTH);
    }

    public JButton makeNewEqButton() {
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

    public JButton makeDeleteButton() {
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

    public JButton makeSaveButton() {
        JButton saveButton = new JButton("Save Data");
        saveButton.setToolTipText("Saves list of all current equations to file");
        saveButton.setFocusable(false);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This should save equations to file!");
            }
        });
        return saveButton;
    }

    public JButton makeLoadButton() {
        JButton loadButton = new JButton("Load Data");
        loadButton.setToolTipText("Loads previous list of equations to file");
        loadButton.setFocusable(false);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("This should load equations from file!");
            }
        });
        return loadButton;
    }


    public void show() {
        frame.setVisible(true);
    }
}

