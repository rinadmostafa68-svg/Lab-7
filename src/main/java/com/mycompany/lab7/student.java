package com.mycompany.lab7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class student extends JFrame {
    private JTextArea welcomeBackTextArea;
    private JButton browse;
    private JButton Logout;
    private JButton Enroll;

    public student() {
        setVisible(true);
        setSize(200,200);
        setContentPane(student);



        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BrowseMyClasses();

            }
        });
        Enroll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnrollNewClass();

            }
        });
        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logout();

            }
        });
    }


    private ArrayList<String> ReadCoursesFromFile(String filename) {
        ArrayList<String> courses = new ArrayList<>();
        try {
            File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
                return courses;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    courses.add(line.trim());
                }
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(student, "Error reading courses: " + e.getMessage());
        }
        return courses;
    }




    private void WriteCoursesToFile(String filename,ArrayList<String> courses) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (String course : courses) {
                writer.write(course + "\n");
            }
            writer.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(student, "Error saving courses: " + e.getMessage());
        }
    }



//there is 1 file name that is set as random name until we merge
    private void BrowseMyClasses() {
        Object avaliblecourses;
        ArrayList<String> enrolledCourses = ReadCoursesFromFile("avaliblecourses.txt");

        if (enrolledCourses.isEmpty()) {
            JOptionPane.showMessageDialog(, "You are not enrolled in any courses yet.\nPlease use 'Enroll in New Class' to add courses.");
            return;
        }

        StringBuilder coursesList = new StringBuilder("Your Current Courses:\n\n");
        for (int i = 0; i < enrolledCourses.size(); i++) {
            coursesList.append((i + 1) + ". ").append(enrolledCourses.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(student,coursesList.toString(),"My Enrolled Classes");
    }


    //there are 2 file name that are set as random name until we merge
    private void EnrollNewClass() {
        ArrayList<String> availableCourses = ReadCoursesFromFile("avalibleCourses.txt");
        ArrayList<String> enrolledCourses = ReadCoursesFromFile("enrolledCourses.txt");
        ArrayList<String> enrollableCourses = new ArrayList<>();
        for (String course : availableCourses) {
            if (!enrolledCourses.contains(course)) {
                enrollableCourses.add(course);
            }
        }

        if (enrollableCourses.isEmpty()) {
            JOptionPane.showMessageDialog(student, "No available courses for enrollment at this time.\nAll courses are full or you are already enrolled in all available courses.");
            return;
        }

        String[] courseArray = enrollableCourses.toArray(new String[0]);
        String selectedCourse = (String) JOptionPane.showInputDialog(
                student,
                "Select a course to enroll:",
                "Enroll in New Class",
                null,
                courseArray,
                courseArray[0]
        );

        if (selectedCourse != null) {
            enrolledCourses.add(selectedCourse);
            WriteCoursesToFile("enrolledcourses.txt", enrolledCourses);

            JOptionPane.showMessageDialog(student, "Successfully enrolled in: " + selectedCourse);
        }
    }




    private void Logout() {
        int confirm = JOptionPane.showConfirmDialog(
                student,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            student.dispose();
            JOptionPane.showMessageDialog(null, "You have been logged out successfully.");
        }

}
