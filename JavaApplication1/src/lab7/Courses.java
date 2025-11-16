/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.lap7;

/**
 *
 * @author Zaid&Lama&joody
 */
public class Courses {
    String courseId;
    String describtion;
    String instructorId;
    List<lesson> lessons;
    List<Stirn> students;

    public Courses(String courseId, String describtion, String instructorId) {
        this.courseId = courseId;
        this.describtion = describtion;
        this.instructorId = instructorId;
        this.lessons =new ArrayList<>();
        this.students =new ArrayList<>();
    }
    
    
}
