/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab7;

/**
 *
 * @author Pc
 */
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JSONdataBase {

    private final String usersPath;
    private final String coursesPath;

    public JSONdataBase(String usersPath, String coursesPath) {
        this.usersPath = usersPath;
        this.coursesPath = coursesPath;

        createFileIfNotExists(usersPath);
        createFileIfNotExists(coursesPath);

        initializeIfEmpty(usersPath, new JSONObject());
        initializeIfEmpty(coursesPath, new JSONObject());
    }

    private void createFileIfNotExists(String path) {
        try {
            File f = new File(path);
            File parent = f.getParentFile();
            if (parent != null && !parent.exists()) parent.mkdirs();
            if (!f.exists()) f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeIfEmpty(String path, JSONObject defaultObj) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            if (content.trim().isEmpty()) saveJSON(path, defaultObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject loadJSON(String path) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            if (content.trim().isEmpty()) return new JSONObject();
            return new JSONObject(content);
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    private void saveJSON(String path, JSONObject obj) {
        try (FileWriter fw = new FileWriter(path)) {
            fw.write(obj.toString(4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addUser(User user) {
        JSONObject usersDB = loadJSON(usersPath);

        JSONObject obj = new JSONObject();
        obj.put("userId", user.getUserId());
        obj.put("role", user.getRole());
        obj.put("username", user.getUserName());
        obj.put("email", user.getEmail());
        obj.put("passwordHash", user.getPassword());

        if (user instanceof Student) {
            Student s = (Student) user;

            obj.put("enrolledCourses", new JSONArray(s.getEnrolledCourses()));

            JSONObject progressJson = new JSONObject();
            for (String course : s.getProgress().keySet()) {
                progressJson.put(course, new JSONArray(s.getProgress().get(course)));
            }
            obj.put("progress", progressJson);
        }

        if (user instanceof Instructor) {
            Instructor ins = (Instructor) user;
            obj.put("createdCourses", new JSONArray(ins.getCreatedCourses()));
        }

        usersDB.put(user.getUserId(), obj);
        saveJSON(usersPath, usersDB);
    }

    public User getUserById(String userId) {
        JSONObject usersDB = loadJSON(usersPath);
        if (!usersDB.has(userId)) return null;

        JSONObject u = usersDB.getJSONObject(userId);
        String role = u.getString("role");

        if (role.equals("student")) {
            Student s = new Student(
                u.getString("userId"),
                u.getString("username"),
                u.getString("email"),
                u.getString("passwordHash")
            );

            JSONArray enrolled = u.getJSONArray("enrolledCourses");
            for (int i = 0; i < enrolled.length(); i++) {
                s.enrollCourse(enrolled.getString(i));
            }

            JSONObject prog = u.getJSONObject("progress");
            for (String courseId : prog.keySet()) {
                JSONArray arr = prog.getJSONArray(courseId);
                for (int i = 0; i < arr.length(); i++) {
                    s.markLessonCompleted(courseId, arr.getString(i));
                }
            }

            return s;
        }

        if (role.equals("instructor")) {
            Instructor ins = new Instructor(
                u.getString("userId"),
                u.getString("username"),
                u.getString("email"),
                u.getString("passwordHash")
            );

            JSONArray created = u.getJSONArray("createdCourses");
            for (int i = 0; i < created.length(); i++) {
                ins.addCourse(created.getString(i));
            }

            return ins;
        }

        return null;
    }

    public User getUserByEmail(String email) {
        JSONObject usersDB = loadJSON(usersPath);
        for (String key : usersDB.keySet()) {
            JSONObject obj = usersDB.getJSONObject(key);
            if (obj.getString("email").equalsIgnoreCase(email)) {
                return getUserById(key);
            }
        }
        return null;
    }

    public void updateUser(User user) {
        addUser(user);
    }

    public void addCourse(Course c) {
        JSONObject coursesDB = loadJSON(coursesPath);

        JSONObject obj = new JSONObject();
        obj.put("courseId", c.getCourseId());
        obj.put("title", c.getTitle());
        obj.put("description", c.getDescription());
        obj.put("instructorId", c.getInstructorId());

        JSONArray lessonsArray = new JSONArray();
        for (Lesson l : c.getLessons()) {
            JSONObject jo = new JSONObject();
            jo.put("lessonId", l.getLessonId());
            jo.put("title", l.getTitle());
            jo.put("content", l.getContent());
            lessonsArray.put(jo);
        }
        obj.put("lessons", lessonsArray);

        obj.put("students", new JSONArray(c.getStudents()));

        coursesDB.put(c.getCourseId(), obj);
        saveJSON(coursesPath, coursesDB);
    }

    public Course getCourseById(String courseId) {
        JSONObject coursesDB = loadJSON(coursesPath);
        if (!coursesDB.has(courseId)) return null;

        JSONObject o = coursesDB.getJSONObject(courseId);

        Course c = new Course(
            o.getString("courseId"),
            o.getString("title"),
            o.getString("description"),
            o.getString("instructorId")
        );

        JSONArray lessons = o.getJSONArray("lessons");
        for (int i = 0; i < lessons.length(); i++) {
            JSONObject l = lessons.getJSONObject(i);
            c.addLesson(new Lesson(
                l.getString("lessonId"),
                l.getString("title"),
                l.getString("content")
            ));
        }

        JSONArray students = o.getJSONArray("students");
        for (int i = 0; i < students.length(); i++) {
            c.enrollStudent(students.getString(i));
        }

        return c;
    }

    public void updateCourse(Course course) {
        addCourse(course);
    }

    public void removeCourse(String courseId) {
        JSONObject coursesDB = loadJSON(coursesPath);
        coursesDB.remove(courseId);
        saveJSON(coursesPath, coursesDB);
    }
}