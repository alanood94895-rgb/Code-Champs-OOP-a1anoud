package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Department;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static ObjectOrientedProgramming.OOPDemo.Services.UniversityService.university;

public class CourseService {
    static Scanner scanner = new Scanner(System.in);
    Department department = new Department();

    public List<Course> getCourses(){
        if (department.getOfferedCourses() == null) {
            department.setOfferedCourses(new ArrayList<>());
        }
        return department.getOfferedCourses();
    }



    public Course addNewCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("** Adding new Course to the department **");

        Course course = new Course();
        course.setId(UUID.randomUUID());

        System.out.println("Enter course name");
        String courseName = scanner.nextLine();
        course.setName(courseName);

        System.out.println("Enter course code");
        String courseCode = scanner.nextLine();
        course.setCourseCode(courseCode);

        return course;

    }

    public List<Course> addNewCourses() {
        List<Course> courseList = getCourses();
        Boolean continueFlag = true;
        while (continueFlag) {
            //System.out.println("Entering multiple courses");
            courseList.add(addNewCourse());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_COURSE);
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }

        return courseList;
    }

    public void displayCourses(){

        for (Course c : getCourses()) {
            System.out.println("Course Id: " + c.getId());
            System.out.println("Course Name: " + c.getName());
            System.out.println("Course Code: " + c.getCourseCode());
        }
    }
    public Course updateCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter course code to update:");
        String code = scanner.nextLine();

        Course existingCourse = findCourseByCourseCode(code);
        if (existingCourse == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return null;
        }

        Course updatedCourse = new Course();
        updatedCourse.setId(existingCourse.getId());

        System.out.println("Enter new Course Code:");
        updatedCourse.setCourseCode(scanner.nextLine());
        System.out.println("Enter new Nama:");
        updatedCourse.setName(scanner.nextLine());

        modifyCourse(code, updatedCourse);
        return updatedCourse;

    }

    public Boolean modifyCourse(String courseCode, Course updatedCourse) {
        Course existingCourse = findCourseByCourseCode(courseCode);
        if (!existingCourse.getCourseCode().equals(null)) {
            existingCourse.setName(updatedCourse.getName());
            existingCourse.setCourseCode(updatedCourse.getCourseCode());
            System.out.println(Constants.COURSE_UPDATED_SUCCESSFULLY);
            return true;
        }

        System.out.println(Constants.COURSE_UPDATE_FAILED);
        return false;
    }

    public Boolean deleteCourse(){
        System.out.println("Enter Course to Remove: ");
        Scanner scanner = new Scanner(System.in);
        Course courseToRemove = findCourseByCourseCode(scanner.nextLine());
        Boolean status = getCourses().remove(courseToRemove);
        System.out.println(status.equals(true) ? Constants.COURSE_DELETED_SUCCESSFULLY : Constants.COURSE_DELETE_FAILED);
        return status;


    }


    public Course findCourseByCourseCode(String courseCode) {

        for (Course c : department.getOfferedCourses()) {
            if (c.getCourseCode().equals(courseCode)) {
                return c;
            }
        }
        System.out.println(Constants.COURSE_NOT_FOUND);
        return null;
    }

    public Boolean handleCouresMenu(Integer cpurseOption){
        switch (cpurseOption){
            case 1-> {
                addNewCourse();
            }
            case 2->{
               updateCourse();
            }
            case 3 ->{
                System.out.println("Show Course ");
                displayCourses();
            }

            case 4-> {
                deleteCourse();
                System.out.println("Show Course ");
                university.displayDepartments();
            }

            case 5->{
                System.out.println("Show Course ");
                university.displayCourses();
            }
            case 6 ->{
                return false;
            }

        }
        return true;
    }
}