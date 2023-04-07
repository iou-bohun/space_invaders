package org.newdawn.spaceinvaders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.JPanel;

public class UserDB extends JFrame implements ActionListener {

    private static Connection conn;

    static {
        String url = "jdbc:mysql://localhost:3306/space-invaders";
        String user = "user1";
        String password = "12345";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return conn;
    }

    private JFrame loginFrame;
    private JLabel titleLabel, idLabel, pwLabel, confirmPwLabel;
    private JTextField idField;
    private JPasswordField pwField, confirmPwField;
    private JButton loginButton, registerButton;
    private JPanel loginPanel,mainPanel,buttonPanel;

    public UserDB() {
        Dimension frameDim = new Dimension(800,600);

        loginFrame = new JFrame("Login System");
        loginFrame.setPreferredSize(frameDim);
        loginFrame.setLayout(null);

        //로그인 패널

        idLabel = new JLabel("ID:");
        pwLabel = new JLabel("Password:");
        confirmPwLabel = new JLabel("Confirm Password:");

        idField = new JTextField();
        pwField = new JPasswordField();
        confirmPwField = new JPasswordField();



        //버튼 패널

        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        //메인 패널
        titleLabel = new JLabel("SPACE INVADERS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        idLabel.setBounds(275,200,50,25);
        idField.setBounds(425,200,100,25);
        pwLabel.setBounds(275,250,100,25);
        pwField.setBounds(425,250,100,25);
        confirmPwLabel.setBounds(275,300,125,25);
        confirmPwField.setBounds(425,300,100,25);
        loginButton.setBounds(275,350,100,25);
        registerButton.setBounds(425,350,100,25);
        titleLabel.setBounds(265,100,300,50);

        loginFrame.add(titleLabel);
        loginFrame.add(idLabel);
        loginFrame.add(idField);
        loginFrame.add(pwLabel);
        loginFrame.add(pwField);
        loginFrame.add(confirmPwLabel);
        loginFrame.add(confirmPwField);
        loginFrame.add(loginButton);
        loginFrame.add(registerButton);

        // add action listener to buttons
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        // set window properties
        loginFrame.pack();
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String id = idField.getText();
            String pw = new String(pwField.getPassword());

            try {
                Connection conn = UserDB.getConnection();
                String query = "SELECT * FROM userdata WHERE id = ? AND password = ?";
                PreparedStatement pstmt = conn.prepareStatement(query);

                pstmt.setString(1, id);
                pstmt.setString(2, pw);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect ID or password!");
                }

                rs.close();
                pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == registerButton) {
            String id = idField.getText();
            String pw = new String(pwField.getPassword());
            String confirmPw = new String(confirmPwField.getPassword());

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

                int result = pstmt.executeUpdate();

                if (result > 0) {
                    JOptionPane.showMessageDialog(this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(this, "Registration failed!");
                }

                pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new UserDB();
    }
}