package ui;

import javax.swing.*;
import java.awt.*;

// A window for help
// // Graphic from: https://freevivedsmileys.wixsite.com/freevived-smileys-de
public class HelpWindow {

    private JFrame frame;
    private JPanel mainPanel;

    public HelpWindow() {
        setFrame();
        setMainPanel();
    }

    public void show() {
        frame.setVisible(true);
    }

    private void setFrame() {
        frame = new JFrame();
        frame.setTitle("Help Window");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new BorderLayout(5, 5));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }

    private void setMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(Color.black);

        JLabel title = new JLabel("<html>HELP ME!!!");
        title.setForeground(Color.red);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 50));

        ImageIcon smiley = new ImageIcon("./data/free_smiley_help.png");
        Image smileyImage = smiley.getImage();
        Image newSmileyImage = smileyImage.getScaledInstance(450, 450,  java.awt.Image.SCALE_SMOOTH);
        smiley = new ImageIcon(newSmileyImage);

        title.setIcon(smiley);
        title.setIconTextGap(10);
        title.setHorizontalTextPosition(SwingConstants.CENTER);
        title.setVerticalTextPosition(SwingConstants.BOTTOM);

        mainPanel.add(title);

        frame.add(mainPanel, BorderLayout.CENTER);
    }
}
