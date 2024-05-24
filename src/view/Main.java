package view;

import reposition.CourseRepository;
import service.CourseService;
import service.CourseView;

public class Main {
    public static void main(String[] args) {
        CourseRepository courseRepository = new CourseRepository();
        CourseService courseService = new CourseService(courseRepository);
        CourseView courseView = new CourseView(courseService);
        courseView.processMenu();
    }
}
