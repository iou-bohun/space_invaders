package org.newdawn.spaceinvaders;

import javax.swing.*;

public class GamePanel extends JPanel {
    public GamePanel(){
        setLayout(null);
        // Tell AWT not to bother repainting our canvas since we're
        // going to do that our self in accelerated mode
    }
}
