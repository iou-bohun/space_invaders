package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class UIKeyHandler extends KeyAdapter{
    GameLobbyPanel glp;
    public UIKeyHandler(GameLobbyPanel glp) {
        this.glp = glp;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (glp.gameState == glp.titleState) {
            if (code == KeyEvent.VK_RIGHT) {
                if(glp.mu.commandNum != 4) {
                glp.mu.commandNum++;
                    if (glp.mu.commandNum > 3) {
                        glp.mu.commandNum = 3;
                    }
                }
            }

            if (code == KeyEvent.VK_LEFT) {
                if(glp.mu.commandNum != 4) {
                    glp.mu.commandNum--;
                    if (glp.mu.commandNum < 0) {
                        glp.mu.commandNum = 0;
                    }
                }
            }

            if (code == KeyEvent.VK_UP) {
                if (glp.mu.commandNum == 3) {
                    glp.mu.commandNum = 4;
                }
            }

            if (code == KeyEvent.VK_DOWN) {
                if (glp.mu.commandNum == 4) {
                    glp.mu.commandNum = 3;
                }
            }

            if (glp.gameState == glp.shopState) {
            }
        }
    }
}
