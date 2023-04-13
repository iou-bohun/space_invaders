package org.newdawn.spaceinvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class GameLobbyPanel extends JPanel implements ActionListener,Runnable {

    Thread gameThread;
    JLabel logo;
    JButton gameStart, selectShip, goShop, changeNick, record, exitGame;
    JFrame lobbyFrame;
    int gameState = 1;
    final int titleState = 1;
    final int shopState = 2;
    final int selectShipState = 3;
    final int scoreRecordState = 4;
    final int screenWidth = 800;
    final int screenHeight = 600;
    public MainUI mu = new MainUI(this);
    public UIKeyHandler key = new UIKeyHandler(this);


    LoginFrame lf;

    public GameLobbyPanel(){
        //addFrame();
        this.setPreferredSize(new Dimension(800,600));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        //paintComponent(g);
        /*setLayout(null);
        setBackground(Color.black);

        logo = new JLabel("SPACE INVADERS");
        mu.setFontNeo(logo,50f);

        gameStart = new JButton("Game Start");
        selectShip = new JButton("Select Ship");
        goShop = new JButton("Shop");
        changeNick = new JButton("<html><body style='text-align:center;'>Change<br />Nickname</body></html>");
        record = new JButton("<html><body style='text-align:center;'>Score<br />Record</body></html>");
        exitGame = new JButton("Save & Exit");

        logo.setBounds(225,100,400,50);
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
        add(exitGame);*/
    }
    public void addFrame(){
        lobbyFrame = new JFrame("Space Invaders");
        lobbyFrame.add(this);
        lobbyFrame.setPreferredSize(new Dimension(800,600));
        lobbyFrame.pack();
        lobbyFrame.setLocation(LoginFrame.frameLocation);
        lobbyFrame.setVisible(true);
        lobbyFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        lobbyFrame.setResizable(false);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        while (gameThread != null){
            update();
            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update(){

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        mu.draw(g2);
        g2.dispose();
    }

    public void actionPerformed(ActionEvent e){
        Connection conn = UserDB.getConnection();
        //진행도 저장 후 종료
        if(e.getSource() == exitGame){
            try {
                String dataSave = "UPDATE userdata SET nickname = ?, best_score = ?, coin = ?, is_hard_ship = ?,is_lucky_ship = ?, HP_potion = ?, speed_potion = ? , selected_ship = ? WHERE id = ?";
                PreparedStatement pstmt = conn.prepareStatement(dataSave);

                pstmt.setString(1, UserDB.nickname);
                pstmt.setInt(2, UserDB.best_score);
                pstmt.setInt(3, UserDB.coin);
                pstmt.setBoolean(4, UserDB.is_hard_ship);
                pstmt.setBoolean(5, UserDB.is_lucky_ship);
                pstmt.setInt(6, UserDB.HP_potion);
                pstmt.setInt(7, UserDB.speed_potion);
                pstmt.setInt(8, UserDB.selected_ship);
                pstmt.setString(9, UserDB.userID);

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
        }

        //임시 코인 테스트용
        else if (e.getSource() == selectShip){
            UserDB.coin = UserDB.coin + 1000;
            System.out.println(UserDB.coin);
        }

        //닉네임 변경 시스템
        else if (e.getSource() == changeNick) {
            String dupnic = "";

            String changeNicLabel = JOptionPane.showInputDialog(this,"Write Nickname to Change", "");
            if (changeNicLabel.length() > 10 || changeNicLabel.length() < 5) {
                JOptionPane.showMessageDialog(this, "Nickname is too long or short!\nNickname must be at least 5 and not more than 10.");
                return;
            }

            try {
                String dupCheck = "SELECT nickname FROM userdata WHERE nickname = ?";
                PreparedStatement dpstmt = conn.prepareStatement(dupCheck);

                dpstmt.setString(1, changeNicLabel);

                ResultSet dprs = dpstmt.executeQuery();
                while (dprs.next()){
                    dupnic = dprs.getString("nickname");
                }
                if(dupnic.equals("")){
                    try {
                        String query = "UPDATE userdata SET nickname = ? WHERE nickname = ?";
                        PreparedStatement pstmt = conn.prepareStatement(query);

                        pstmt.setString(1, changeNicLabel);
                        pstmt.setString(2, UserDB.nickname);

                        int result = pstmt.executeUpdate();

                        if (result > 0) {
                            JOptionPane.showMessageDialog(this, "Successfully Changed!");
                            UserDB.nickname = changeNicLabel;
                        } else {
                            JOptionPane.showMessageDialog(this, "Changing Failed.");
                        }

                        pstmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "Same nickname exists.");
                }

            }
            catch (SQLException ex){
                ex.printStackTrace();
            }

        }
    }
}
