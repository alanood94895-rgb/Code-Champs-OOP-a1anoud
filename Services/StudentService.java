package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Department;
import ObjectOrientedProgramming.OOPDemo.Entities.Student;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static ObjectOrientedProgramming.OOPDemo.Services.UniversityService.university;

public class StudentService {

    private final CourseService courseService = new CourseService();
    private final DepartmentService departmentService = new DepartmentService();
    private final Scanner scanner = new Scanner(System.in);

    public List<Student> getStudents() {
        if (university.getStudentList() == null) {
            university.setStudentList(new ArrayList<>());
        }
        return university.getStudentList();
    }

    // Add one student
    public Student addNewStudent() {
        System.out.println(" ** Adding new Student **");

        Student student = new Student();
        student.setId(UUID.randomUUID());

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        student.setName(name);

        System.out.println("Departments List:");
        university.displayDepartments();

        System.out.print("Select or create department: ");
        Department department = departmentService.addNewDepartment();
        student.setDepartment(department);

        // Assign courses from department
        if (department != null) {
            student.setCourseList(department.getOfferedCourses());
        }

        getStudents().add(student);
        System.out.println(Constants.STUDENT_ADDED_SUCCESSFULLY);

        return student;
    }

    // Add multiple students
    public List<Student> addNewStudents() {
        List<Student> studentsList = new ArrayList<>();

        boolean continueFlag = true;

        while (continueFlag) {
            studentsList.add(addNewStudent());

            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_STUDENTS);
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }

        return studentsList;
    }

    // Update student
    public Student updateStudent() {
        System.out.print("Enter student name to update: ");
        String name = scanner.nextLine();

        Student existingStudent = findStudentByName(name);

        if (existingStudent == null) {
            System.out.println(Constants.STUDENT_NOT_FOUND);
            return null;
        }

        System.out.print("Enter new student name: ");
        String newName = scanner.nextLine();

        existingStudent.setName(newName);

        System.out.println("Update courses:");
        List<Course> newCourses = courseService.addNewCourses();
        existingStudent.setCourseList(newCourses);

        System.out.println(Constants.STUDENT_UPDATED_SUCCESSFULLY);
        return existingStudent;
    }

    // Delete student
    public boolean deleteStudent() {
        System.out.print("Enter student name to remove: ");
        String name = scanner.nextLine();

        Student studentToRemove = findStudentByName(name);

        if (studentToRemove == null) {
            System.out.println(Constants.STUDENT_NOT_FOUND);
            return false;
        }

        boolean status = getStudents().remove(studentToRemove);

        System.out.println(
                status ? Constants.STUDENT_DELETED_SUCCESSFULLY
                        : Constants.STUDENT_DELETED_FAILED
        );

        return status;
    }

    // Find student
    public Student findStudentByName(String studentName) {
        for (Student s : getStudents()) {
            if (s.getName() != null &&
                    s.getName().equalsIgnoreCase(studentName)) {
                return s;
            }
        }
        return null;
    }

    // Display one student
    public void displayStudentByName() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student student = findStudentByName(name);

        if (student == null) {
            System.out.println(Constants.STUDENT_NOT_FOUND);
            return;
        }

        System.out.println("Name: " + student.getName());
        System.out.println("Phone: " + student.getPhoneNumber());
        System.out.println("ID Card: " + student.getIdCard());
        System.out.println("Email: " + student.getEmail());
        System.out.println("DOB: " + student.getDateOfBirth());
        System.out.println("Department: " + student.getDepartment());
        System.out.println("Courses: " + student.getCourseList());
    }

    // Menu handler
    public boolean handleStudentMenu(int studentOption) {

        switch (studentOption) {
            case 1 -> addNewStudent();

            case 2 -> updateStudent();

            case 3 -> {
                System.out.println("Show student:");
                displayStudentByName();
            }

            case 4 -> {
                deleteStudent();
                System.out.println("All students:");
                university.displayStudents();
            }

            case 5 -> {
                System.out.println("All students:");
                university.displayStudents();
            }

            case 6 -> {
                return false;
            }

            default -> System.out.println("Invalid option!");
        }

        return true;
    }
}