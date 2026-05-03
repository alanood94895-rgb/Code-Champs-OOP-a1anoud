package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Department;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static ObjectOrientedProgramming.OOPDemo.Services.UniversityService.university;

public class CourseService {

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
        Scanner scanner = new Scanner(System.in);
        List<Course> courseList = new ArrayList<>();
        Boolean continueFlag = true;
        while (continueFlag) {
            courseList.add(addNewCourse());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_COURSE);
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return courseList;
    }

    public Boolean updateCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Departments List");
        for (int i = 0; i < university.getDepartments().size(); i++) {
            System.out.println((i + 1) + ". " + university.getDepartments().get(i).getName());
        }

        System.out.println("Enter Department Number:");
        Integer deptNumber = scanner.nextInt();
        scanner.nextLine();

        Department department = university.getDepartments().get(deptNumber - 1);

        System.out.println("Courses List");
        for (int i = 0; i < department.getOfferedCourses().size(); i++) {
            System.out.println((i + 1) + ". " + department.getOfferedCourses().get(i).getName());
        }

        System.out.println("Enter Course Number to update:");
        Integer courseNumber = scanner.nextInt();
        scanner.nextLine();

        Course course = department.getOfferedCourses().get(courseNumber - 1);
        if (course.getId() != null) {
            System.out.println("What do you want to update?");
            System.out.println("1- Name");
            System.out.println("2- Code");
            Integer choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.println("Enter new Course Name:");
                    course.setName(scanner.nextLine());
                }
                case 2 -> {
                    System.out.println("Enter new Course Code:");
                    course.setCourseCode(scanner.nextLine());
                }
            }
            System.out.println(Constants.COURSE_UPDATED_SUCCESSFULLY);
            return true;
        }
        System.out.println(Constants.COURSE_NOT_FOUND);
        return false;
    }

    public Boolean deleteCourse() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Departments List");
        for (int i = 0; i < university.getDepartments().size(); i++) {
            System.out.println((i + 1) + ". " + university.getDepartments().get(i).getName());
        }

        System.out.println("Enter Department Number:");
        Integer deptNumber = scanner.nextInt();
        scanner.nextLine();

        Department department = university.getDepartments().get(deptNumber - 1);

        System.out.println("Courses List");
        for (int i = 0; i < department.getOfferedCourses().size(); i++) {
            System.out.println((i + 1) + ". " + department.getOfferedCourses().get(i).getName());
        }

        System.out.println("Enter Course Number to delete:");
        Integer courseNumber = scanner.nextInt();
        scanner.nextLine();

        Course courseToDelete = department.getOfferedCourses().get(courseNumber - 1);
        if (courseToDelete.getId() != null) {
            return department.removeCourse(courseToDelete);
        }
        System.out.println(Constants.COURSE_NOT_FOUND);
        return false;
    }

    public void displayCourseByName() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Course Name to display:");
        String courseName = scanner.nextLine().trim();

        for (Department department : university.getDepartments()) {
            for (Course course : department.getOfferedCourses()) {
                if (course.getName().equalsIgnoreCase(courseName)) {
                    System.out.println("Course Name: " + course.getName());
                    System.out.println("Course Code: " + course.getCourseCode());
                    System.out.println("Department: " + department.getName());
                    return;
                }
            }
        }
        System.out.println(Constants.COURSE_NOT_FOUND);
    }

    public void displayAllCourses() {
        System.out.println("** Courses List **");
        for (Department department : university.getDepartments()) {
            for (Course course : department.getOfferedCourses()) {
                System.out.println("Course Name: " + course.getName());
                System.out.println("Course Code: " + course.getCourseCode());
                System.out.println("Department: " + department.getName());
            }
        }
    }

    public Boolean handleCourseMenu(Integer courseOption) {
        switch (courseOption) {
            case 1 -> {
                addNewCourse();
            }
            case 2 -> {
                updateCourse();

            }
            case 3 -> {

                deleteCourse();
            }
            case 4 -> {

                displayCourseByName();
            }
            case 5 -> {
                displayAllCourses();
            }
            case 6 ->{
                return false;
            }
        }
        return true;
    }

}