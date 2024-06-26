package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A window for creating a new equation
public class NewEqWindow {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel northPanel;
    private PhysicsWindow physicsWindow;

    // MODIFIES: this
    // EFFECTS: Initializes the main window
    public NewEqWindow(PhysicsWindow physicsWindow) {
        setFrame();
        setMainPanel();
        setNorthPanel();
        this.physicsWindow = physicsWindow;
    }

    // MODIFIES: this
    // EFFECTS: shows window
    public void show() {
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the frame
    private void setFrame() {
        frame = new JFrame();
        frame.setTitle("New Equation Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout(5, 5));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: Sets the north panel
    private void setNorthPanel() {
        northPanel = new JPanel();
        northPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        northPanel.setBackground(Color.ORANGE);

        JLabel title = new JLabel("Choose Equation Type!");
        title.setForeground(Color.BLACK);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 20));

        northPanel.add(title);

        frame.add(northPanel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: Sets the main panel with all the buttons
    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.setBackground(Color.GRAY);

        mainPanel.add(this.makeForceEqButton());
        mainPanel.add(this.makeDensityEqButton());
        mainPanel.add(this.makeFlowRateEqButton());

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: Makes force equation button
    private JButton makeForceEqButton() {
        JButton newEqButton = new JButton("Force");
        newEqButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        newEqButton.setFocusable(false);
        newEqButton.setToolTipText("Make Force Equation");
        newEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsWindow.makeEquation("Force");
                frame.dispose();
            }
        });

        return newEqButton;
    }

    // MODIFIES: this
    // EFFECTS: Makes density equation button
    private JButton makeDensityEqButton() {
        JButton newEqButton = new JButton("Density");
        newEqButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        newEqButton.setFocusable(false);
        newEqButton.setToolTipText("Make Density Equation");
        newEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsWindow.makeEquation("Density");
                frame.dispose();
            }
        });

        return newEqButton;
    }

    // MODIFIES: this
    // EFFECTS: Makes flow rate equation button
    private JButton makeFlowRateEqButton() {
        JButton newEqButton = new JButton("Flow Rate");
        newEqButton.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        newEqButton.setFocusable(false);
        newEqButton.setToolTipText("Make Flow Rate Equation");
        newEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                physicsWindow.makeEquation("Flow Rate");
                frame.dispose();
            }
        });

        return newEqButton;
    }

}
