package org.newdawn.spaceinvaders;

import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;

public class RegisterPanel extends JPanel{

    JLabel nicknameLabel, ridLabel,rpwLabel,confirmPwLabel,rtitleLabel;
    JTextField ridField,nicknameField;
    JPasswordField rpwField,confirmPwField;
    JButton cancel,registerButton;

    public RegisterPanel(){
        setLayout(null);

        ridLabel = new JLabel("ID:");
        rpwLabel = new JLabel("Password:");
        confirmPwLabel = new JLabel("Confirm Password:");
        nicknameLabel = new JLabel("Nickname:");

        ridField = new JTextField();
        rpwField = new JPasswordField();
        confirmPwField = new JPasswordField();
        nicknameField = new JTextField();

        registerButton = new JButton("Register");
        cancel = new JButton("Cancel");

        rtitleLabel = new JLabel("REGISTER");
        rtitleLabel.setFont(new Font("Arial", Font.BOLD, 30));

        //setBounds로 컴포넌트 크기, 위치 조정
        nicknameLabel.setBounds(275,150,100,25);
        nicknameField.setBounds(425,150,100,25);
        ridLabel.setBounds(275,200,50,25);
        ridField.setBounds(425,200,100,25);
        rpwLabel.setBounds(275,250,100,25);
        rpwField.setBounds(425,250,100,25);
        confirmPwLabel.setBounds(275,300,125,25);
        confirmPwField.setBounds(425,300,100,25);
        registerButton.setBounds(275,350,100,25);
        cancel.setBounds(425,350,100,25);
        rtitleLabel.setBounds(265,100,300,50);

        add(nicknameField);
        add(nicknameLabel);
        add(rtitleLabel);
        add(ridLabel);
        add(ridField);
        add(rpwLabel);
        add(rpwField);
        add(confirmPwLabel);
        add(confirmPwField);
        add(cancel);
        add(registerButton);
    }
}
