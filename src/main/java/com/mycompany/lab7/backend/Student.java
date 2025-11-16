/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.lap7;

/**
 *
 * @author Zaid&Lama&joody
 */
 class Student extends User {
     private List<String> enrolledcourses;
     private List<Stirng> progress;
 
 public student(String userId, String username, String email, String passwordHash){
     super(userId,"student",username,email,passwordHash);
     this.enrolledcourses=new ArrayList<>();
     this.progress=nem ArrayList<>();         
 }  
    public List<String> getenrolledcourses(){return enrolledcourses;}
    public List<String> getprogress(){return progress;}
}
