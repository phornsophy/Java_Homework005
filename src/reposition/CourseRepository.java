package reposition;

import model.Course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CourseRepository {
    private List<Course> courses;
    private int nextId;
    public CourseRepository() {
        this.courses = new ArrayList<>();
        this.nextId = 1000;
    }
    public Course addCourse(String title, String[] instructorNames, String[] requirements) {
        Integer id = generateId();
        Date startDate = new Date();
        Course course = new Course(id, title, instructorNames, requirements, startDate);
        courses.add(course);
        return course;
    }
    private Integer generateId() {

        return new Random().nextInt(1000);
    }
    public void removeCourseById(int id) {
        courses.removeIf(course -> course.getId().equals(id));
    }
    public
    List<Course> getAllCourses() {

        return courses;
    }
}
