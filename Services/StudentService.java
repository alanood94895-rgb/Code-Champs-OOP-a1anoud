package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Department;
import ObjectOrientedProgramming.OOPDemo.Entities.Student;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class StudentService {
    CourseService courseService = new CourseService();
    DepartmentService departmentService = new DepartmentService();


    public Student addNewStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(" ** Adding new Student **");
        //object student class
        Student student = new Student();
        student.setId(UUID.randomUUID());

        System.out.println("Enter student Name: ");
        String stdName = scanner.nextLine();

        System.out.println("Departments List");
        UniversityService.university.displayDepartments();

        System.out.println("Enter Department: ");
        student.setDepartment(departmentService.addNewDepartment());

        student.setCourseList(student.getDepartment().getOfferedCourses());
        return student;
    }

    public List<Student> addNewStudents() {
        Scanner scanner = new Scanner(System.in);
        List<Student> studentsList = new ArrayList<>();

        Boolean continueFlag = true;
        while (continueFlag) {
            studentsList.add(addNewStudent());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_STUDENTS);
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return studentsList;
    }

    public Department updateStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student name to update:");
        String name = scanner.nextLine();

        Department existingDept = findStudentByName(name);
        if (existingDept == null) {
            System.out.println(Constants.STUDENT_NOT_FOUND);
            return null;
        }

        System.out.println("Enter new student name:");
        String newName = scanner.nextLine();

        Student updatedStudent = new Student();
        updatedStudent.setId(existingDept.getId());
        updatedStudent.setName(newName);
        updatedStudent.setOfferedCourses(courseService.addNewCourses());

        modifyStudent(name, updatedStudent);
        return updatedStudent;
    }

}




