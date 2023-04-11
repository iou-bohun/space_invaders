package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPanel extends JPanel implements ActionListener {

    JButton buyHardShip, buyLuckyShip, buyHpPotion, buySpeedPotion, returnLobby;
    JLabel shopLogo,coinLabel;

    public ShopPanel(){
        setLayout(null);
        setBackground(Color.black);

        shopLogo = new JLabel("SHOP");
        shopLogo.setFont(new Font("Arial", Font.BOLD, 30));
        shopLogo.setForeground(Color.blue);

        coinLabel = new JLabel("0");
        coinLabel.setText(Integer.toString(UserDB.coin));
        coinLabel.setFont(new Font("Arial", Font.BOLD, 30));
        coinLabel.setForeground(Color.yellow);

        buyHardShip = new JButton("<html><body style='text-align:center;'>Purchase<br />Hard Ship</body></html>");
        buyLuckyShip = new JButton("<html><body style='text-align:center;'>Purchase<br />Lucky Ship</body></html>");
        buyHpPotion = new JButton("<html><body style='text-align:center;'>Purchase<br />HP Potion</body></html>");
        buySpeedPotion = new JButton("<html><body style='text-align:center;'>Purchase<br />Speed Potion</body></html>");
        returnLobby = new JButton("Return");

        coinLabel.setBounds(600,100,75,50);
        shopLogo.setBounds(350,100,100,50);
        buyHpPotion.setBounds(250,200,100,100);
        buySpeedPotion.setBounds(450,200,100,100);
        buyHardShip.setBounds(250,400,100,100);
        buyLuckyShip.setBounds(450,400,100,100);
        returnLobby.setBounds(50,50,75,50);

        buyHardShip.addActionListener(this);
        buyLuckyShip.addActionListener(this);
        buyHpPotion.addActionListener(this);
        buySpeedPotion.addActionListener(this);

        add(coinLabel);
        add(shopLogo);
        add(buyHardShip);
        add(buyLuckyShip);
        add(buyHpPotion);
        add(buySpeedPotion);
        add(returnLobby);
    }

    /*protected void paintComponent(Graphics g){
        g.drawString("COIN" + UserDB.coin, 600, 100);
        g.setColor(Color.yellow);
        g.fillRect(0,0,800,600);
    }*/

    public void actionPerformed(ActionEvent e){
        coinLabel.setText(Integer.toString(UserDB.coin));
        //HP 포션 구입
        if(e.getSource() == buyHpPotion){
            if(UserDB.coin > 50) {
                UserDB.HP_potion++;
                UserDB.coin = UserDB.coin - 50;
            }
            else {
                JOptionPane.showMessageDialog(this, "You don't have enough Coin.");
            }
        }
        //스피드 포션 구입
        else if(e.getSource() == buySpeedPotion){
            if(UserDB.coin > 50) {
                UserDB.speed_potion++;
                UserDB.coin = UserDB.coin - 50;
            }
            else {
                JOptionPane.showMessageDialog(this, "You don't have enough Coin.");
            }
        }
        else if(e.getSource() == buyHardShip){
            if(UserDB.is_hard_ship == false && UserDB.coin > 1000) {
                int saveConfirm = JOptionPane.showConfirmDialog(this, "Purchase Hard Ship.","",JOptionPane.YES_NO_OPTION);
                if (saveConfirm == JOptionPane.YES_OPTION) {
                    UserDB.is_hard_ship = true;
                    UserDB.coin = UserDB.coin - 1000;
                }
            }

            else if (UserDB.is_hard_ship == false && UserDB.coin < 1000) {
                JOptionPane.showMessageDialog(this, "You don't have enough Coin.");
            }

            else {
                JOptionPane.showMessageDialog(this, "You already have a Hard Ship.");
            }

        }

        else if(e.getSource() == buyLuckyShip){
            if(UserDB.is_lucky_ship == false && UserDB.coin > 1500) {
                int saveConfirm = JOptionPane.showConfirmDialog(this, "Purchase Speed Ship.","",JOptionPane.YES_NO_OPTION);
                if (saveConfirm == JOptionPane.YES_OPTION) {
                    UserDB.is_lucky_ship = true;
                    UserDB.coin = UserDB.coin - 1500;
                }
            }

            else if (UserDB.is_lucky_ship == false && UserDB.coin < 1500) {
                JOptionPane.showMessageDialog(this, "You don't have enough Coin.");
            }

            else {
                JOptionPane.showMessageDialog(this, "You already have a Lucky Ship.");
            }

        }
    }
}
