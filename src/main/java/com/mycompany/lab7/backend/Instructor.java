/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

import java.util.ArrayList;

/**
 *
 * @author Pc
 */
public class Instructor extends User {
    private ArrayList<String> courses;

    public Instructor(ArrayList<String> createdCourses, String userId, String role, String userName, String email, String password) {
        super(userId, role, userName, email, password);
        this.courses = createdCourses;
    }

    public ArrayList<String> getCreatedCourses() {
        return courses;
    }

    public void setCreatedCourses(ArrayList<String> createdCourses) {
        this.courses = createdCourses;
    }
   public void addCource(String courseId)
   {
       if(!courses.contains(courseId))
           courses.add(courseId);
   }
   public void removeCourse(String courseId)
   {
       courses.remove(courseId);
   }
    
}
