package org.newdawn.spaceinvaders;

import javax.swing.*;

public class MainFrame {
    public MainFrame() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Space Invaders");

        GameLobbyPanel glp = new GameLobbyPanel();
        window.add(glp);

        window.pack();

        window.setLocationRelativeTo(null);
        //window.setLocation(LoginFrame.frameLocation);
        window.setVisible(true);
        glp.startGameThread();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
