package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A window for help
// // Graphic from: https://freevivedsmileys.wixsite.com/freevived-smileys-de
public class NewEqWindow {

    private JFrame frame;
    private JPanel mainPanel;
    private JPanel northPanel;
    private PhysicsWindow physicsWindow;


    public NewEqWindow(PhysicsWindow physicsWindow) {
        setFrame();
        setMainPanel();
        setNorthPanel();
        this.physicsWindow = physicsWindow;
    }

    public void show() {
        frame.setVisible(true);
    }

    private void setFrame() {
        frame = new JFrame();
        frame.setTitle("New Equation Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setLayout(new BorderLayout(5, 5));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

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

    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.setBackground(Color.GRAY);

        mainPanel.add(this.makeForceEqButton());
        mainPanel.add(this.makeDensityEqButton());
        mainPanel.add(this.makeFlowRateEqButton());

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    private JButton makeForceEqButton() {
        JButton newEqButton = new JButton("Force");
        newEqButton.setFocusable(false);
        newEqButton.setToolTipText("Make Force Equation");
        newEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                physicsWindow.makeEquation("Force");
            }
        });

        return newEqButton;
    }

    private JButton makeDensityEqButton() {
        JButton newEqButton = new JButton("Density");
        newEqButton.setFocusable(false);
        newEqButton.setToolTipText("Make Density Equation");
        newEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                physicsWindow.makeEquation("Density");
            }
        });

        return newEqButton;
    }

    private JButton makeFlowRateEqButton() {
        JButton newEqButton = new JButton("Flow Rate");
        newEqButton.setFocusable(false);
        newEqButton.setToolTipText("Make Flow Rate Equation");
        newEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                physicsWindow.makeEquation("Flow Rate");
            }
        });

        return newEqButton;
    }

}
