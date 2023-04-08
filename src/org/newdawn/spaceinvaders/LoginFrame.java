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
    }

    public static void main(String[] args) throws Exception{
        new LoginFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == register.cancel){
            card.show(getContentPane(),"Login");
        }
        else if(e.getSource() == login.registerButton){
            card.show(getContentPane(), "Register");
        }
    }
}

