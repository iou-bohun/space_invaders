package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameLobbyPanel extends JPanel implements ActionListener {
    JLabel logo;
    JButton gameStart, selectShip, goShop, changeNick, record, exitGame;

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
        //goShop.addActionListener(this);
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
        Connection conn = UserDB.getConnection();
        //게임 데이터 DB에 저장 후 종료 메세지 출력
        if(e.getSource() == exitGame){
            try {
                String dataSave = "UPDATE userdata SET nickname = ?, stage_process = ?, stage1_best_score = ?, stage2_best_score = ?, stage3_best_score = ?, stage4_best_score = ?, stage5_best_score = ?,coin = ?, is_hard_ship = ?,is_lucky_ship = ?, HP_potion = ?, speed_potion = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(dataSave);

                pstmt.setString(1, UserDB.nickname);
                pstmt.setInt(2, UserDB.stage_process);
                pstmt.setInt(3, UserDB.stage1_best_score);
                pstmt.setInt(4, UserDB.stage2_best_score);
                pstmt.setInt(5, UserDB.stage3_best_score);
                pstmt.setInt(6, UserDB.stage4_best_score);
                pstmt.setInt(7, UserDB.stage5_best_score);
                pstmt.setInt(8, UserDB.coin);
                pstmt.setBoolean(9, UserDB.is_hard_ship);
                pstmt.setBoolean(10, UserDB.is_lucky_ship);
                pstmt.setInt(11, UserDB.HP_potion);
                pstmt.setInt(12, UserDB.speed_potion);
                pstmt.setString(13,UserDB.userID);

                int updateResult = pstmt.executeUpdate();

                //종료 메세지 출력
                if (updateResult > 0) {
                    int saveConfirm = JOptionPane.showConfirmDialog(this, "Save complete.\nWanna exit?","Save Complete",JOptionPane.YES_NO_OPTION);

                    if (saveConfirm == JOptionPane.YES_OPTION) {
                        System.out.println(UserDB.coin + " " + UserDB.is_hard_ship + " " + UserDB.is_lucky_ship + " " + UserDB.HP_potion + "" + UserDB.speed_potion);
                        System.exit(0);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Saving Failed.");
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        } /*else if (e.getSource() == gameStart) {
            LoginFrame lf = new LoginFrame();
            lf.card.show(lf.getContentPane(), "Game");
            Game g = new Game();
            g.gameLoop();

        }*/
        else if (e.getSource() == selectShip){
            UserDB.coin = UserDB.coin + 1000;
            System.out.println(UserDB.coin);
        }
    }
}
