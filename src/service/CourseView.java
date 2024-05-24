package service;

import exception.CourseException;
import model.Course;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.Arrays;
import java.util.Scanner;

public class CourseView {
    private CourseService courseService;
    public CourseView(CourseService courseService) {

        this.courseService = courseService;
    }
    public void displaytiitle(){
        String reset = "\u001B[0m";
        String cyanBold = "\u001B[1;36m";
        String fontEnglish = "\u001B[3m";
        System.out.println(cyanBold + "");
        System.out.println(cyanBold + " ".repeat(43) + " ██╗  ██╗ ██████╗ ███╗   ███╗███████╗██╗    ██╗ ██████╗ ██████╗ ██╗  ██╗");
        System.out.println(cyanBold + " ".repeat(43) + " ██║  ██║██╔═══██╗████╗ ████║██╔════╝██║    ██║██╔═══██╗██╔══██╗██║ ██╔╝");
        System.out.println(cyanBold + " ".repeat(43) + " ███████║██║   ██║██╔████╔██║█████╗  ██║ █╗ ██║██║   ██║██████╔╝█████╔╝ ");
        System.out.println(cyanBold + " ".repeat(43) + " ██╔══██║██║   ██║██║╚██╔╝██║██╔══╝  ██║███╗██║██║   ██║██╔══██╗██╔═██╗ ");
        System.out.println(cyanBold + " ".repeat(43) + " ██║  ██║╚██████╔╝██║ ╚═╝ ██║███████╗╚███╔███╔╝╚██████╔╝██║  ██║██║  ██╗");
        System.out.println(cyanBold + " ".repeat(43) + " ╚═╝  ╚═╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝ ╚══╝╚══╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝" + reset);
    }
    public void displayMenu() {

        String cyanBold = "\u001B[1;36m";
        System.out.println(cyanBold+"===================================================================================================================================================================");
        System.out.println(cyanBold+"Welcome to Course Management System");
        System.out.println(cyanBold+"===================================================================================================================================================================");
        System.out.println(cyanBold+"[1]✤."+"Add new course");
        System.out.println(cyanBold+"[2]✤."+"List courses");
        System.out.println(cyanBold+"[3]✤."+"Find course by Id");
        System.out.println(cyanBold+"[4]✤."+"Find course by title");
        System.out.println(cyanBold+"[5]✤."+"Remove course by id");
        System.out.println(cyanBold+"[0,99]✤."+"Exit");
    }
    public void processMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        displaytiitle();
        while (!exit) {
            displayMenu();
            System.out.print("[+].Enter your choice: ");
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        addCourse(scanner);
                        break;
                    case 2:
                        listCourses();
                        break;
                    case 3:
                        findCourseById(scanner);
                        break;
                    case 4:
                        findCourseByTitle(scanner);
                        break;
                    case 5:
                        removeCourseById(scanner);
                        break;
                    case 0:
                    case 99:
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
            }
        }
        scanner.close();
    }
    private void addCourse(Scanner scanner) {
        System.out.print("[+]✷.Insert course title: ");
        String title = scanner.nextLine();
        System.out.print("[+]✷.Insert instructor names: ");
        String[] instructorNames = scanner.nextLine().split(",");
        System.out.print("[+]✷.Insert course requirements: ");
        String[] requirements = scanner.nextLine().split(",");

        try {
            courseService.addCourse(title, instructorNames, requirements);
            System.out.println("Course added successfully.");
        } catch (CourseException e) {
            System.out.println("Failed to add course: " + e.getMessage());
        }
    }
    private void listCourses() {
        String reset = "\u001B[0m";
        String cyanBold = "\u001B[1;36m";
        Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
        table.addCell(cyanBold+"ID",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyanBold+"TITLE",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyanBold+"INSTRUCTOR",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyanBold+"REQUIREMENT",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyanBold+"START DATE",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.setColumnWidth(0,25,40);
        table.setColumnWidth(1,25,40);
        table.setColumnWidth(2,25,40);
        table.setColumnWidth(3,25,40);
        table.setColumnWidth(4,25,40);
        courseService.getAllCourses().forEach(course -> {
            String yellowColor = "\u001B[33m";
            String resetColor = "\u001B[0m";
            table.addCell(yellowColor+course.getId().toString()+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor+course.getTitle()+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor+String.join(", ", course.getInstructorNames())+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor+String.join(", ", course.getRequirements())+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor+course.getStartDate().toString()+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER));
        });
        System.out.println(table.render());
    }
    private void findCourseById(Scanner scanner) {
        System.out.print("[+].Insert course id: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Course course = courseService.findCourseById(id);
        if (course != null) {
            String reset = "\u001B[0m";
            String cyanBold = "\u001B[1;36m";
            Table table = new Table(5, BorderStyle.UNICODE_BOX_HEAVY_BORDER, ShownBorders.ALL);
            table.addCell(cyanBold+"ID",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(cyanBold+"TITLE",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(cyanBold+"INSTRUCTOR",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(cyanBold+"REQUIREMENT",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(cyanBold+"START DATE",new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.setColumnWidth(0,25,40);
            table.setColumnWidth(1,25,40);
            table.setColumnWidth(2,25,40);
            table.setColumnWidth(3,25,40);
            table.setColumnWidth(4,25,40);
            String yellowColor = "\u001B[33m"; //code for colunm
            String resetColor = "\u001B[0m"; // Reset color
            table.addCell(yellowColor + course.getId().toString() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getTitle() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER)); // Corrected line
            table.addCell(yellowColor + String.join(", ", course.getInstructorNames()) + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + String.join(", ", course.getRequirements()) + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            table.addCell(yellowColor + course.getStartDate().toString() + resetColor, new CellStyle(CellStyle.HorizontalAlign.CENTER));
            System.out.println(table.render());
        } else {
            System.out.println("Course with ID " + id + " not found.");
        }
    }
    private void findCourseByTitle(Scanner scanner) {
        System.out.print("Enter course title: ");
        String title = scanner.nextLine().toLowerCase();
        String reset = "\u001B[0m";
        String cyanBold = "\u001B[1;36m";
        Table table = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
        table.addCell(cyanBold+"ID",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyanBold+"TITLE",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyanBold+"INSTRUCTOR",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyanBold+"REQUIREMENT",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.addCell(cyanBold+"START DATE",new CellStyle(CellStyle.HorizontalAlign.CENTER));
        table.setColumnWidth(0,25,40);
        table.setColumnWidth(1,25,40);
        table.setColumnWidth(2,25,40);
        table.setColumnWidth(3,25,40);
        table.setColumnWidth(4,25,40);
        String yellowColor = "\u001B[33m"; //code for colunm
        String resetColor = "\u001B[0m"; // Reset color
        courseService.getAllCourses()
                .stream()
                .filter(course -> course.getTitle().toLowerCase().contains(title))
                .forEach(course -> {
                    table.addCell(yellowColor+String.valueOf(course.getId())+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER));
                    table.addCell(yellowColor+course.getTitle()+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER));
                    table.addCell(yellowColor+Arrays.toString(course.getInstructorNames()),new CellStyle(CellStyle.HorizontalAlign.CENTER));
                    table.addCell(yellowColor+ Arrays.toString(course.getRequirements())+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER));
                    table.addCell(yellowColor+course.getStartDate().toString()+resetColor,new CellStyle(CellStyle.HorizontalAlign.CENTER)); // Assuming startDate is a Date or String
                });
        System.out.println(table.render());
    }
    private void removeCourseById(Scanner scanner) {
        System.out.print("Enter course id to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        courseService.removeCourseById(id);
        System.out.println("Course removed successfully.");
    }
}
