package model;

import java.util.Arrays;
import java.util.Date;

public class Course {
    private Integer id;
    private String title;
    private String[] instructorNames;
    private String[] requirements;
    private Date startDate;

    public Course(Integer id, String title, String[] instructorNames, String[] requirements, Date startDate) {
        this.id= Integer.valueOf(id);
        this.title = title;
        this.instructorNames = instructorNames;
        this.requirements = requirements;
        this.startDate = startDate;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String[] getInstructorNames() {
        return instructorNames;
    }

    public String[] getRequirements() {
        return requirements;
    }

    public Date getStartDate() {

        return startDate;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public void setTitle(String title) {

        this.title = title;
    }
    public void setInstructorNames(String[] instructorNames) {

        this.instructorNames = instructorNames;
    }
    public void setRequirements(String[] requirements) {

        this.requirements = requirements;
    }
    public void setStartDate(Date startDate) {

        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", instructorNames=" + Arrays.toString(instructorNames) +
                ", requirements=" + Arrays.toString(requirements) +
                ", startDate=" + startDate +
                '}';
    }
}
