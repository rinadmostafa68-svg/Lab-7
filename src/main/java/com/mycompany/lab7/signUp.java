package com.mycompany.lab7;

import javax.swing.*;
import java.io.*;

public class signUp extends JFrame {
    private JTextArea signUpWithAnTextArea;
    private JTextField roleTextField;
    private JTextField Role;
    private JTextField emailTextField;
    private JTextField Email;
    private JPasswordField Password;
    public signUp(){
        setVisible(true);
        setSize(200,200);
        setContentPane(signUp);



        String Email = Email.getText().trim();
        String Password = new String(Password.getPassword());
        String Role = Role.getText().trim();
        // if he doesnt eneter any details
        if (Email.isEmpty() || Password.isEmpty() || Role.isEmpty()) {
            JOptionPane.showMessageDialog(signUp, "Please enter all fields for signup");
            return;
        }
        // if user already exists
        if (UserExists(Email)) {
            JOptionPane.showMessageDialog(signUp, "User already exists. Please login instead.");
            return;
        }
        //saving the data
        if (SaveUser(Email, Password, Role)) {
            JOptionPane.showMessageDialog(signUp, "Signup successful! You can now login.");
        }
        else {
            JOptionPane.showMessageDialog(signUp, "Signup failed. Please try again.");}




    }
    // check user function
    private boolean UserExists(String email) {
        try {
            File databaseFile = new File("users.txt");
            if (!databaseFile.exists()) {
                return false;
            }

            BufferedReader reader = new BufferedReader(new FileReader(databaseFile));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length >= 1) {
                    String storedEmail = parts[0].trim();
                    if (storedEmail.equals(email)) {
                        reader.close();
                        return true;
                    }
                }
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    private boolean SaveUser(String email, String password, String role) {
        try {
            File databaseFile = new File("users.txt");
            FileWriter writer = new FileWriter(databaseFile, true);

            // Format: email:password:role
            String userData = email + ":" + password + ":" + role + "\n";
            writer.write(userData);
            writer.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(signUp, "Error saving user: " + e.getMessage(), );
            return false;
        }
    }
}
