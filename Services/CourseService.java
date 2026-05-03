package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Teacher;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

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
            //System.out.println("Entering multiple courses");
            courseList.add(addNewCourse());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_COURSE);
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }

        return courseList;
    }
    public Course updateCourses() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Courses name to update: ");
        String name = scanner.nextLine();

        Course courses = findCourseName(name);

        if (courses == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return null;
        }

        System.out.print("Enter new name: ");
        courses.setName(scanner.nextLine());

        System.out.println("Update courses:");
        courses.setCourseList(courseService.addNewCourses());

        System.out.println(Constants.COURSE_UPDATED_SUCCESSFULLY);

        return courses;
    }


    public boolean deleteCourse() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter course name to delete: ");
        String name = scanner.nextLine();

        Course course = findCourseByName(name);

        if (course == null) {
            System.out.println(Constants.COURSE_NOT_FOUND);
            return false;
        }

        boolean removed = getCourse().remove(course);

        System.out.println(removed ? Constants.COURSE_DELETED_SUCCESSFULLY : Constants.COURSE_DELETED_FAILED);

        return removed;
    }

    public Teacher findTeacherByName(String name) {
        if (name == null) return null;

        for (Teacher t : getTeachers()) {
            if (t.getName() != null && t.getName().equalsIgnoreCase(name)) {
                return t;
            }
        }
        return null;
    }

    public void displayTeacherByName() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter teacher name: ");
        String name = scanner.nextLine();

        Teacher teacher = findTeacherByName(name);

        if (teacher == null) {
            System.out.println(Constants.TEACHER_NOT_FOUND);
            return;
        }

        System.out.println("Name: " + teacher.getName());
        System.out.println("Phone: " + teacher.getPhoneNumber());
        System.out.println("Email: " + teacher.getEmail());
        System.out.println("Department: " + teacher.getDepartment());
        System.out.println("Courses: " + teacher.getCourseList());
    }


    private void displayAllTeachers() {
        System.out.println("All teachers:");
        university.displayTeachers();
    }


    public boolean handleTeacherMenu(int option) {

        switch (option) {

            case 1 -> addNewTeacher();

            case 2 -> updateTeacher();

            case 3 -> {
                System.out.println("Show teacher:");
                displayTeacherByName();
            }

            case 4 -> {
                deleteTeacher();
                displayAllTeachers();
            }

            case 5 -> displayAllTeachers();

            case 6 -> {
                return false;
            }

            default -> System.out.println("Invalid option!");
        }

        return true;
    }
}




}
