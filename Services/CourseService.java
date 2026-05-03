package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Teacher;
import ObjectOrientedProgramming.OOPDemo.Entities.University;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CourseService {
    University university = new University();

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

    public String updateCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Constants.COURSE_UPDATED_SUCCESSFULLY);
        String courseNameToUpdate = scanner.nextLine();

        List<Course> courseList = new ArrayList<>();
        System.out.println(Constants.COURSE_UPDATED_SUCCESSFULLY);
        String newCourseName = scanner.nextLine();

        for (int i =0; i< courseList.size()-1; i++){
            String oldCourseName = String.valueOf(courseList.get(i));
            if (oldCourseName.equals(courseNameToUpdate) ){
                oldCourseName = newCourseName;
            }
        }
        return newCourseName;
    }

    public String deleteCourse(){
        Scanner scanner = new Scanner(System.in);
        System.out.println(Constants.COURSE_DELETED_SUCCESSFULLY);
        String courseNameToDelete = scanner.nextLine();

        List<Course> courseList = new ArrayList<>();

        for (int i =0; i< courseList.size()-1; i++){
            String oldCourseName = String.valueOf(courseList.get(i));
            if (oldCourseName.equals(courseNameToDelete) ){
                courseList.remove(courseNameToDelete);
            }
        }
        return courseNameToDelete +" " + "DELETED";

    }

    public Boolean handleCourseMenu(Integer courseOption) {
        DepartmentService departmentService = new DepartmentService();
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        CourseService courseService = new CourseService();


        switch (courseOption) {
            case 1 -> {
                System.out.println("Add new course");
                courseService.addNewCourse();
            }
            case 2 -> {
                System.out.println("Updated course");
                courseService.updateCourse();
            }
            case 3 -> {
                System.out.println("Show Courses");
                university.displayCourses();
            }

            case 4 -> {
                System.out.println("Delete Course");
                courseService.deleteCourse();
            }

            case 5 -> {
                return false;
            }
        }
        return true;
    }

}