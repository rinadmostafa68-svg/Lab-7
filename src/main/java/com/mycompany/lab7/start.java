package com.mycompany.lab7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class start extends JFrame {
    private JPanel start;
    private JTextField welcomeTextField;
    private JButton signUpButton;
    private JButton loginButton;
    public start(){
        setVisible(true);
        setSize(200,200);
        setContentPane(start);
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                setVisible(false);
            }
        });
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new signUp();
                setVisible(false);
            }
        });
    }

}

