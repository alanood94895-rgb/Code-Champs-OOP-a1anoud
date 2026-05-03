package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Teacher;
import ObjectOrientedProgramming.OOPDemo.Entities.University;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static ObjectOrientedProgramming.OOPDemo.Services.UniversityService.university;
public class TeacherService {

    private static int counter = 1;
    University university = new University();

    private final DepartmentService departmentService = new DepartmentService();
    private final CourseService courseService = new CourseService();
    private final Scanner scanner = new Scanner(System.in);


    public List<Teacher> getTeachers() {
        if (university.getTeacherList() == null) {
            university.setTeacherList(new ArrayList<>());
        }
        return university.getTeacherList();
    }

    public Teacher addNewTeacher() {

        System.out.println("** Adding new teacher **");

        Teacher teacher = new Teacher();
        teacher.setId(UUID.randomUUID());

        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        teacher.setName(name);

        System.out.print("Enter email: ");
        teacher.setEmail(scanner.nextLine());

        System.out.print("Enter phone number: ");
        teacher.setPhoneNumber(scanner.nextLine());

        teacher.setTeacherId("TH-" + counter++);

        System.out.println("Departments:");
        university.displayDepartments();

        teacher.setDepartment(departmentService.addNewDepartment());

        System.out.println("Assign courses:");
        teacher.setCourseList(courseService.addNewCourses());

        getTeachers().add(teacher);

        System.out.println(Constants.TEACHER_ADDED_SUCCESSFULLY);

        return teacher;
    }

    public Teacher updateTeacher() {

        System.out.print("Enter teacher name to update: ");
        String name = scanner.nextLine();

        Teacher teacher = findTeacherByName(name);

        if (teacher == null) {
            System.out.println(Constants.TEACHER_NOT_FOUND);
            return null;
        }

        System.out.print("Enter new name: ");
        teacher.setName(scanner.nextLine());

        System.out.println("Update courses:");
        teacher.setCourseList(courseService.addNewCourses());

        System.out.println(Constants.TEACHER_UPDATED_SUCCESSFULLY);

        return teacher;
    }


    public boolean deleteTeacher() {

        System.out.print("Enter teacher name to delete: ");
        String name = scanner.nextLine();

        Teacher teacher = findTeacherByName(name);

        if (teacher == null) {
            System.out.println(Constants.TEACHER_NOT_FOUND);
            return false;
        }

        boolean removed = getTeachers().remove(teacher);

        System.out.println(removed ? Constants.TEACHER_DELETED_SUCCESSFULLY : Constants.TEACHER_DELETED_FAILED);

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

