package ui;

import model.Equations;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // PhysicsPlayer physicsPlayer = new PhysicsPlayer();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PhysicsWindow main = new PhysicsWindow();
            }
        });
    }
}
