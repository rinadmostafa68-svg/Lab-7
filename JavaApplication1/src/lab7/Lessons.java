/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.lap7;

/**
 *
 * @author Zaid&Lama&joody
 */
public class Lessons {
    Stirng lessonId;
    String title;
    String content;
    List<Sring> resources;

    public Lessons(Stirng lessonId, String title, String content) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = new ArrayList<>();
    }
    
}
