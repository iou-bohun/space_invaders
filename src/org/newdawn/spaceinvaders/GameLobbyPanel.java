package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLobbyPanel extends JPanel implements ActionListener {
    JLabel logo;
    JButton gameStart, selectShip, goShop, changeNick, record, exitGame;
    //private LoginFrame llfg;

    public GameLobbyPanel(){
        setLayout(null);
        setBackground(Color.black);

        logo = new JLabel("SPACE INVADERS");
        logo.setFont(new Font("Arial", Font.BOLD, 30));

        gameStart = new JButton("Game Start");
        selectShip = new JButton("Select Ship");
        goShop = new JButton("Shop");
        changeNick = new JButton("<html><body style='text-align:center;'>Change<br />Nickname</body></html>");
        record = new JButton("<html><body style='text-align:center;'>Score<br />Record</body></html>");
        exitGame = new JButton("Save & Exit");

        logo.setBounds(265,100,300,50);
        gameStart.setBounds(200,300,100,75);
        selectShip.setBounds(350,300,100,75);
        goShop.setBounds(500,300,100,75);
        changeNick.setBounds(200,425,100,75);
        record.setBounds(350,425,100,75);
        exitGame.setBounds(500,425,100,75);

        //gameStart.addActionListener(this);
        selectShip.addActionListener(this);
        goShop.addActionListener(this);
        changeNick.addActionListener(this);
        record.addActionListener(this);
        exitGame.addActionListener(this);

        add(logo);
        add(gameStart);
        add(selectShip);
        add(goShop);
        add(changeNick);
        add(record);
        add(exitGame);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == exitGame){
            System.exit(0);
        } /*else if (e.getSource() == gameStart) {
            LoginFrame lf = new LoginFrame();
            lf.card.show(lf.getContentPane(), "Game");
            Game g = new Game();
            g.gameLoop();

        }*/
    }
}
