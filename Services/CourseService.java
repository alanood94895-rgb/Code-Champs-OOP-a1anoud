package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static ObjectOrientedProgramming.OOPDemo.Services.UniversityService.university;

public class CourseService {

    private final Scanner scanner = new Scanner(System.in);


    public List<Course> getCourses() {
        if (university.getCourseList() == null) {
            university.setCourseList(new ArrayList<>());
        }
        return university.getCourseList();
    }

    public Course addNewCourse() {

        System.out.println("** Adding new Course **");

        Course course = new Course();
        course.setId(UUID.randomUUID());

        System.out.print("Enter course name: ");
        course.setName(scanner.nextLine());

        System.out.print("Enter course code: ");
        course.setCourseCode(scanner.nextLine());

        getCourses().add(course);

        System.out.println(Constants.COURSE_ADDED_SUCCESSFULLY);

        return course;
    }


    public List<Course> addNewCourses() {

        List<Course> courseList = new ArrayList<>();
        boolean running = true;

        while (running) {
            courseList.add(addNewCourse());

            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_COURSE);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                running = false;
            }
        }

        return courseList;
    }

    public Course updateCourse() {

        System.out.print("Enter course name to update: ");
        String name = scanner.nextLine();

        Course course = findCourseByName(name);

        if (course == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return null;
        }

        System.out.print("Enter new name: ");
        course.setName(scanner.nextLine());

        System.out.print("Enter new course code: ");
        course.setCourseCode(scanner.nextLine());

        System.out.println(Constants.COURSE_UPDATED_SUCCESSFULLY);

        return course;
    }


    public boolean deleteCourse() {

        System.out.print("Enter course name to delete: ");
        String name = scanner.nextLine();

        Course course = findCourseByName(name);

        if (course == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return false;
        }

        boolean removed = getCourses().remove(course);

        System.out.println(removed ? Constants.COURSE_DELETED_SUCCESSFULLY : Constants.COURSE_DELETE_FAILED);

        return removed;
    }

    public Course findCourseByName(String name) {

        if (name == null) return null;

        for (Course c : getCourses()) {
            if (c.getName() != null && c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }

        return null;
    }

    // =========================
    // DISPLAY ONE COURSE
    // =========================
    public void displayCourseByName() {

        System.out.print("Enter course name: ");
        String name = scanner.nextLine();

        Course course = findCourseByName(name);

        if (course == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return;
        }

        System.out.println("Name: " + course.getName());
        System.out.println("Code: " + course.getCourseCode());
    }

    // =========================
    // DISPLAY ALL COURSES
    // =========================
    private void displayAllCourses() {
        System.out.println("All courses:");
        university.displayCourses();
    }

    // =========================
    // MENU
    // =========================
    public boolean handleCourseMenu(int option) {

        switch (option) {

            case 1 -> addNewCourse();

            case 2 -> updateCourse();

            case 3 -> {
                System.out.println("Show course:");
                displayCourseByName();
            }

            case 4 -> {
                deleteCourse();
                displayAllCourses();
            }

            case 5 -> displayAllCourses();

            case 6 -> {
                return false;
            }

            default -> System.out.println("Invalid option!");
        }

        return true;
    }
}