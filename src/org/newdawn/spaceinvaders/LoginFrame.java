package org.newdawn.spaceinvaders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.swing.*;
import javax.swing.JPanel;

public class LoginFrame extends JFrame implements ActionListener{
    CardLayout card = new CardLayout();
    LoginPanel login = new LoginPanel();
    RegisterPanel register = new RegisterPanel();
    GameLobbyPanel lobby = new GameLobbyPanel();
    ShopPanel shop = new ShopPanel();
    GamePanel gameP = new GamePanel();
    Font NeoDung;
    public static Point frameLocation;
    public LoginFrame(){
        setMainFrame();
    }

    public void setMainFrame(){
        setTitle("Space Invaders");
        setLayout(card);

        add("Login",login);
        add("Register",register);
        add("Lobby",lobby);
        add("Game",gameP);
        add("Shop",shop);

        setPreferredSize(new Dimension(800,600));
        pack();

        if(!UserDB.is_logged_in) {setLocationRelativeTo(null);}
        else {setLocation(frameLocation);}
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        login.loginButton.addActionListener(this);
        login.registerButton.addActionListener(this);
        register.cancel.addActionListener(this);
        register.registerButton.addActionListener(this);
        lobby.gameStart.addActionListener(this);
        lobby.goShop.addActionListener(this);
        shop.returnLobby.addActionListener(this);

        if(UserDB.is_logged_in){
            card.show(getContentPane(),"Lobby");
        }
        //폰트 설정 - 네오둥근모
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/NeoDunggeunmoPro-Regular.ttf");
            NeoDung = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception{
        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        //DB 커넥션 생성
        Connection conn = UserDB.getConnection();
        //로그인 버튼 - 성공 시 로비패널 이동
        if(e.getSource() == login.loginButton){

            String id = login.idField.getText();
            String pw = new String(login.pwField.getPassword());
            //로그인 시도
            try {
                String query = "SELECT id,password FROM userdata WHERE id = ? AND password = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1, id);
                pstmt.setString(2, pw);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    String dataLoad = "SELECT * FROM userdata WHERE id = ?";
                    PreparedStatement pstmt2 = conn.prepareStatement(dataLoad);

                    pstmt2.setString(1, id);

                    ResultSet rs2 = pstmt2.executeQuery();
                    //플레이어 데이터 로드 - 데이터베이스 테이블에서 데이터 로드, UserDB의 static 변수에 저장
                    while (rs2.next()) {
                        UserDB.userID = id;
                        UserDB.nickname = rs2.getString("nickname");
                        UserDB.best_score = rs2.getInt("best_score");
                        UserDB.coin = rs2.getInt("coin");
                        UserDB.is_hard_ship = rs2.getBoolean("is_hard_ship");
                        UserDB.is_lucky_ship = rs2.getBoolean("is_lucky_ship");
                        UserDB.HP_potion = rs2.getInt("HP_potion");
                        UserDB.speed_potion = rs2.getInt("speed_potion");
                        UserDB.selected_ship = rs2.getInt("selected_ship");

                        //데이터 로드 실험
                        System.out.println(UserDB.coin + " " + UserDB.is_hard_ship + " " + UserDB.is_lucky_ship + " " + UserDB.HP_potion + "" + UserDB.speed_potion);
                        JOptionPane.showMessageDialog(this, "Login successful!");
                        card.show(getContentPane(), "Lobby");
                        UserDB.is_logged_in = true;
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect ID or password!");
                }

                rs.close();
                pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
        //회원가입 버튼 - 회원가입 패널 이동
        else if(e.getSource() == login.registerButton){
            card.show(getContentPane(), "Register");

        }
        //회원가입 취소 - 로그인 패널 이동
        else if(e.getSource() == register.cancel){
            card.show(getContentPane(),"Login");
        }
        //회원 등록 버튼 - 성공 시 로그인 패널 이동
        else if (e.getSource() == register.registerButton) {
            String id = register.ridField.getText();
            String nic = register.nicknameField.getText();
            String pw = new String(register.rpwField.getPassword());
            String confirmPw = new String(register.confirmPwField.getPassword());

            //중복체크용 문자열
            String dupid = "";
            //String duppw = "";
            //String dupnic = "";

            //부적절한 입력정보 감지
            if (nic.length() < 5 || nic.length() > 10) {
                JOptionPane.showMessageDialog(this, "Nickname is too long or short!\nNickname must be at least 5 and not more than 10.");
                return;
            }

            if (id.length() < 8  || id.length() > 12) {
                JOptionPane.showMessageDialog(this, "ID is too long or short!\nID must be at least 8 and not more than 12.");
                return;
            }

            if (pw.length() < 8 || pw.length() > 12) {
                JOptionPane.showMessageDialog(this, "Passwords is too long or short!\nPassword must be at least 8 and not more than 12.");
                return;
            }

            if (id.equals(pw)) {
                JOptionPane.showMessageDialog(this, "ID and password must be different!");
                return;
            }

            if (!pw.equals(confirmPw)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
                return;
            }

            //데이터베이스 접속, 중복된 ID, 패스워드, 닉네임 있는지 확인
            try {
                String dupCheck = "SELECT id,password,nickname FROM userdata WHERE id = ? OR password = ? OR nickname = ?";
                PreparedStatement dpstmt = conn.prepareStatement(dupCheck);

                dpstmt.setString(1, id);
                dpstmt.setString(2, pw);
                dpstmt.setString(3, nic);

                ResultSet dprs = dpstmt.executeQuery();

                while (dprs.next()){
                    dupid = dprs.getString("id");
                }

                if (dupid.equals("")) {
                    try {
                        String query = "INSERT INTO userdata (id, password, nickname) VALUES (?, ?, ?)";
                        PreparedStatement pstmt = conn.prepareStatement(query);

                        pstmt.setString(1, id);
                        pstmt.setString(2, pw);
                        pstmt.setString(3, nic);

                        int result = pstmt.executeUpdate();

                        if (result > 0) {
                            JOptionPane.showMessageDialog(this, "Registration successful!");
                            card.show(getContentPane(), "Login");
                        } else {
                            JOptionPane.showMessageDialog(this, "Registration failed!");
                        }

                        pstmt.close();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(this, "The same ID or password or nickname exists!");
                }
            }
            catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        //로비 패널 - 게임 시작
        else if (e.getSource() == lobby.gameStart) {
            //card.show(getContentPane(),"Game");
            //프레임 위치 확인
            frameLocation = getLocationOnScreen();
            Thread gameThread = new Thread(new Runnable() {
                public void run() {
                    Game g = new Game();
                    g.gameLoop();
                }
            });
            gameThread.start();
            dispose();
        }
        //상점 이동
        else if (e.getSource() == lobby.goShop) {
            card.show(getContentPane(),"Shop");
            shop.coinLabel.setText(Integer.toString(UserDB.coin));
        }
        //로비 복귀
        else if (e.getSource() == shop.returnLobby) {
            card.show(getContentPane(), "Lobby");
        }
    }
}

