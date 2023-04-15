package org.newdawn.spaceinvaders;

import javax.swing.*;

public class MainFrame {
    public MainFrame() {
        //Frame window = new JFrame();
        //window.dispose();
    }
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Space Invaders");

        GameLobbyPanel glp = new GameLobbyPanel();
        window.add(glp);

        window.pack();

        window.setLocationRelativeTo(null);
        //window.setLocation(glp.frameLocation);
        window.setVisible(true);
        //window.createBufferStrategy(2);

        //if (glp.gameState != glp.inGameState)
        glp.startGameThread();
        //else glp.gameState = glp.titleState;
    }
}

