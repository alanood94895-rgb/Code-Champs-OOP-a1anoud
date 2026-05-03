package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Person;
import ObjectOrientedProgramming.OOPDemo.Entities.Student;
import ObjectOrientedProgramming.OOPDemo.Entities.Teacher;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import static ObjectOrientedProgramming.OOPDemo.Services.UniversityService.university;

public class TeacherService {

    static Integer counter = 1;
    PersonService personService = new PersonService();
    DepartmentService departmentService = new DepartmentService();
    CourseService courseService = new CourseService();

    public Teacher addNewTeacher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("** Adding new teacher **");

        Teacher teacher = (Teacher) personService.addNewPerson();
        teacher.setId(UUID.randomUUID());

        System.out.println("Enter Name");
        String teacherName = scanner.nextLine();
        teacher.setName(teacherName);

        System.out.println("Enter Email");
        String teacherEmail = scanner.nextLine();
        teacher.setEmail(teacherEmail);

        System.out.println("Enter Phone Number");

        teacher.setTeacherId("TH-" + counter);
        counter += 1;

        System.out.println("Departments & Associated Courses: ");
        UniversityService.university.displayDepartments();

        teacher.setDepartment(departmentService.addNewDepartment());

        teacher.setCourseList(courseService.addNewCourses());

        return teacher;
    }

    public List<Teacher> addNewTeachers() {
        Scanner scanner = new Scanner(System.in);
        List<Teacher> teacherList = new ArrayList<>();
        Boolean continueFlag = true;
        while (continueFlag) {
            teacherList.add(addNewTeacher());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_TEACHER);
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return teacherList;
    }

    public Teacher updateTeacher () {
        System.out.print("Enter teacher name to update: ");
        String name = scanner.nextLine();

        Teacher existingTeacher = findTeacherByName(name);

        if (existingTeacher == null) {
            System.out.println(Constants.TEACHER_NOT_FOUND);
            return null;
        }

        System.out.print("Enter new teacher name: ");
        String newName = scanner.nextLine();

        existingTeacher.setName(newName);

        System.out.println("Update courses:");
        List<Course> newCourses = courseService.addNewCourses();
        existingTeacher.setCourseList(newCourses);

        System.out.println(Constants.TEACHER_UPDATED_SUCCESSFULLY);
        return existingTeacher;
    }

    public boolean deleteTeacher() {
        System.out.print("Enter teacher name to remove: ");
        String name = scanner.nextLine();

        Teacher teacherToRemove = findTeacherByName(name);

        if (teacherToRemove == null) {
            System.out.println(Constants.TEACHER_NOT_FOUND);
            return false;
        }

        boolean status = getTeacher().remove(teacherToRemove);

        System.out.println(status ? Constants.TEACHER_DELETED_SUCCESSFULLY : Constants.TEACHER_DELETED_FAILED);

        return status;
    }

    public Student findTeacherByName(String teacherName) {
        for (Student s : getTeacher()) {
            if (s.getName() != null && s.getName().equalsIgnoreCase(teacherName)) {

                return s;
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
        System.out.println("ID Card: " + teacher.getIdCard());
        System.out.println("Email: " + teacher.getEmail());
        System.out.println("DOB: " + teacher.getDateOfBirth());
        System.out.println("Department: " + teacher.getDepartment());
        System.out.println("Courses: " + teacher.getCourseList());
    }

    public boolean handleTeatcherMenu(int studentOption) {
        switch (teacherOption){
            case 1-> {
                addNewTeacher();
            }
            case 2->{
                updateTeacher();
            }
            case 3 ->{
                System.out.println("Show Students ");
                displayStudentByName();
            }

            case 4-> {
                deleteStudent();
                System.out.println("Show Students");
                university.displayDepartments();
            }

            case 5->{
                System.out.println(" Show Students ");
                university.displayDepartments();
            }
            case 6 ->{
                return false;
            }

        }
        return true;
    }


}
