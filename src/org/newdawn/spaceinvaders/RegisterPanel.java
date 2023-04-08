package org.newdawn.spaceinvaders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;
import javax.swing.JPanel;

public class RegisterPanel extends JPanel{

    private JLabel registerLabel;

    JButton cancel = new JButton("Cancel");
    public RegisterPanel(){
        setLayout(null);
        registerLabel = new JLabel("SEX");
        registerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        registerLabel.setBounds(265,100,300,50);
        cancel.setBounds(275,300,100,50);

        add(registerLabel);
        add(cancel);
    }
}
