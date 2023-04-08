package org.newdawn.spaceinvaders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.JPanel;

public class LoginPanel extends JPanel{
    private JLabel titleLabel, idLabel, pwLabel, confirmPwLabel;
    private JTextField idField;
    private JPasswordField pwField, confirmPwField;
    JButton loginButton, registerButton;

    public LoginPanel(){

        setLayout(null);

        idLabel = new JLabel("ID:");
        pwLabel = new JLabel("Password:");
        confirmPwLabel = new JLabel("Confirm Password:");

        idField = new JTextField();
        pwField = new JPasswordField();
        confirmPwField = new JPasswordField();

        //로그인, 회원가입 버튼
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        titleLabel = new JLabel("SPACE INVADERS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        //setBounds로 컴포넌트 크기, 위치 조정
        idLabel.setBounds(275,200,50,25);
        idField.setBounds(425,200,100,25);
        pwLabel.setBounds(275,250,100,25);
        pwField.setBounds(425,250,100,25);
        confirmPwLabel.setBounds(275,300,125,25);
        confirmPwField.setBounds(425,300,100,25);
        loginButton.setBounds(275,350,100,25);
        registerButton.setBounds(425,350,100,25);
        titleLabel.setBounds(265,100,300,50);

        add(titleLabel);
        add(idLabel);
        add(idField);
        add(pwLabel);
        add(pwField);
        add(confirmPwLabel);
        add(confirmPwField);
        add(loginButton);
        add(registerButton);
    }
}
