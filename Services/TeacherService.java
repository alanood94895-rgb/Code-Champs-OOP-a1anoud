package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Person;
import ObjectOrientedProgramming.OOPDemo.Entities.Teacher;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class TeacherService {

    static Integer counter = 1;
    PersonService personService = new PersonService();
    DepartmentService departmentService = new DepartmentService();
    CourseService courseService = new CourseService();

    public Teacher addNewTeacher() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("** Adding new teacher **");

        Teacher teacher = (Teacher) personService.addNewPerson();
        /*teacher.setId(UUID.randomUUID());

        System.out.println("Enter Name");
        String teacherName = scanner.nextLine();
        teacher.setName(teacherName);

        System.out.println("Enter Email");
        String teacherEmail = scanner.nextLine();
        teacher.setEmail(teacherEmail);

        System.out.println("Enter Phone Number");*/

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



}
