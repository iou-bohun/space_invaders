package org.newdawn.spaceinvaders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.JPanel;

public class LoginFrame extends JFrame implements ActionListener{
    CardLayout card = new CardLayout();
    LoginPanel login = new LoginPanel();
    RegisterPanel register = new RegisterPanel();
    UserDB u = new UserDB();
    public LoginFrame(){
        super("Space Invaders 102");
        setLayout(card);

        add("Login",login);
        add("Register",register);

        setSize(800,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        login.loginButton.addActionListener(this);
        login.registerButton.addActionListener(this);
        register.cancel.addActionListener(this);
        register.registerButton.addActionListener(this);
    }

    public static void main(String[] args) throws Exception{
        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == login.loginButton){

                String id = login.idField.getText();
                String pw = new String(login.pwField.getPassword());

                try {
                    Connection conn = UserDB.getConnection();
                    String query = "SELECT id,password FROM userdata WHERE id = ? AND password = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);

                    pstmt.setString(1, id);
                    pstmt.setString(2, pw);

                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        dispose();
                        Game g = new Game();
                        g.gameLoop();

                    } else {
                        JOptionPane.showMessageDialog(this, "Incorrect ID or password!");
                    }

                    rs.close();
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

        }
        else if(e.getSource() == login.registerButton){
            card.show(getContentPane(), "Register");

        }
        else if(e.getSource() == register.cancel){
            card.show(getContentPane(),"Login");

        }
        else if (e.getSource() == register.registerButton) {
            String id = register.ridField.getText();
            //String nic = register.nicknameField.getText();
            String pw = new String(register.rpwField.getPassword());
            String confirmPw = new String(register.confirmPwField.getPassword());

            if (!pw.equals(confirmPw)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
                return;
            }

            try {
                Connection conn = UserDB.getConnection();
                String query = "INSERT INTO userdata (id, password) VALUES (?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1, id);
                pstmt.setString(2, pw);
                //pstmt.setString(2,nic);

                int result = pstmt.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                    card.show(getContentPane(),"Login");
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed!");
                }

                pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }
}

