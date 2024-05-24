package service;

import exception.CourseException;
import model.Course;
import reposition.CourseRepository;

import java.util.List;

public class CourseService {
    private CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    public Course addCourse(String title, String[] instructorNames, String[] requirements) throws CourseException {
        if (title == null || title.isEmpty()) {
            throw new CourseException("Title cannot be empty");
        }
        return courseRepository.addCourse(title, instructorNames, requirements);
    }
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }
    public Course findCourseById(int id) {
        return courseRepository.getAllCourses().stream()
                .filter(course -> course.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public void removeCourseById(int id) {
        courseRepository.removeCourseById(id);
    }
}
