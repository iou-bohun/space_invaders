package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


//임시 테스트용 클래스
public class SelectShipPanel extends JPanel implements KeyListener, ActionListener {
    //private static final long serialVersionUID = 1L;
    private JButton button1, button2, button3, button4;
    private Image uiImage;
    private int currentButton;

    public SelectShipPanel() {
        currentButton = 1;
        setFocusable(true);
        addKeyListener(this);
        setPreferredSize(new Dimension(800, 600));
        uiImage = new ImageIcon("sprites/hard_ship.png").getImage();

        button1 = new JButton();
        button2 = new JButton();
        button3 = new JButton();
        button4 = new JButton();
        button1.setActionCommand("1");
        button2.setActionCommand("2");
        button3.setActionCommand("3");
        button4.setActionCommand("4");
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(uiImage, 220, 290, null);
        switch (currentButton) {
            case 1:
                g.drawRect(220, 290, 120, 30);
                break;
            case 2:
                g.drawRect(420, 290, 120, 30);
                break;
            case 3:
                g.drawRect(220, 330, 120, 30);
                break;
            case 4:
                g.drawRect(420, 330, 120, 30);
                break;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP:
                if (currentButton == 3 || currentButton == 4) {
                    currentButton -= 2;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (currentButton == 1 || currentButton == 2) {
                    currentButton += 2;
                }
                break;
            case KeyEvent.VK_LEFT:
                if (currentButton == 2 || currentButton == 4) {
                    currentButton--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (currentButton == 1 || currentButton == 3) {
                    currentButton++;
                }
                break;
            case KeyEvent.VK_ENTER:
                actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, Integer.toString(currentButton)));
                break;
        }
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        switch (action) {
            case "1":
                System.out.println("Button 1 selected");
                break;
            case "2":
                System.out.println("Button 2 selected");
                break;
            case "3":
                System.out.println("Button 3 selected");
                break;
            case "4":
                System.out.println("Button 4 selected");
                break;
        }
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new SelectShipPanel());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
