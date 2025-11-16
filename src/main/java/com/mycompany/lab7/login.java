package com.mycompany.lab7;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class login extends JFrame {

    private JTextArea enterYourInfromationsTextArea;
    private JTextField emailTextField;
    private JPasswordField Password;
    private JTextField roleTextField;
    private JTextField Role;
    private JPasswordField Email;


    public login(){
        setVisible(true);
        setSize(200,200);
        setContentPane(login);


        //get infromations
        String Email = Email.getText().trim();
        String Password = new String(Password.getPassword());
        String Role = Role.getText().trim();

        if (Email.isEmpty() || Password.isEmpty() || Role.isEmpty()) {
            JOptionPane.showMessageDialog(login,"Please enter all fields");
            return;}

        if (checkCredentials(Email, Password, Role)) {
            // checks if student or instructor
            if(Role.equals("student")){
                setVisible(false);
                 new student();}
            else(){
                setVisible(false);
                new (instructor);}



        }
        else()
        JOptionPane.showMessageDialog(login,"Invalid entry");

    }
    // valdation for the entry
    private boolean checkCredentials(String email, String password, String role) {
        try {
            File databaseFile = new File("users.txt");
            if (!databaseFile.exists()){
                return false;
            }

            BufferedReader reader = new BufferedReader(new FileReader(databaseFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    String storedEmail = parts[0].trim();
                    String storedPassword = parts[1].trim();
                    String storedRole = parts[2].trim();
                    if (storedEmail.equals(email) && storedPassword.equals(password) && storedRole.equals(role)) {
                        reader.close();
                        return true;}
                }}
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(login,"error");
        }
        return false;
    }

}
