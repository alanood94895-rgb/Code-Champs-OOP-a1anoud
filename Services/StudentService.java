package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
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
    public Boolean modifyStudent(String studentName, Student updatedStudent) {
        Student existingStudent = findStudentByName(StudentName);

        if (!existingStudent.getId().equals(null)) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent .setOfferedCourses(updatedStudent.getOfferedCourses());
            System.out.println(Constants.STUDENT_UPDATED_SUCCESSFULLY);
            return true;
        }

        System.out.println(Constants.STUDENT_UPDATED_FAILED);
        return false;
    }

    public Boolean deleteStudent(){
        System.out.println("Enter Student to Remove: ");
        Scanner scanner = new Scanner(System.in);
        Student studentToRemove = findStudentByName(scanner.nextLine());
        Boolean status = getStudents().remove(studentToRemove);
        System.out.println(status.equals(true) ? Constants.STUDENT_DELETED_SUCCESSFULLY : Constants.STUDENT_DELETED_FAILED);
        return status;


    }

    public Student findStudentByName(String studentName) {
        for(Student s : getStudent()){
            if(s.getName().equalsIgnoreCase(studentName)){
                return s;
            }
        }
        return null;
    }

    public void displayStudentByName(){
        System.out.println("Enter Student Name: ");
        Scanner scanner = new Scanner(System.in);
        Student student = findStudentByName(scanner.nextLine());
        System.out.println( "PhoneNumber" + student.getPhoneNumber());
        System.out.println( "IdCard" + student.getIdCard());
        System.out.println( "email" + student.getEmail());
        System.out.println( "dateOfBirth" + student.getDateOfBirth());
        System.out.println( "department" + student.getDepartment());
        System.out.println( "coursList" + student.getCourseList());

        }
    }

    public Boolean handleStudentMenu(Integer studentOption){
        switch (studentOption){
            case 1-> {
                addNewStudent();
            }
            case 2->{
                updateStudent();
            }
            case 3 ->{
                System.out.println("Show Department ");
                displayDepartmentByName();
            }

            case 4-> {
                deleteDepartment();
                System.out.println("Show Departments");
                university.displayDepartments();
            }

            case 5->{
                System.out.println(" Show Departments ");
                university.displayDepartments();
            }
            case 6 ->{
                return false;
            }

        }
        return true;
    }
}

}




