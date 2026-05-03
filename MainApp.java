package ObjectOrientedProgramming.OOPDemo;

import ObjectOrientedProgramming.OOPDemo.Entities.Teacher;
import ObjectOrientedProgramming.OOPDemo.Menu.Menu;
import ObjectOrientedProgramming.OOPDemo.Services.DepartmentService;
import ObjectOrientedProgramming.OOPDemo.Services.StudentService;
import ObjectOrientedProgramming.OOPDemo.Services.TeacherService;
import ObjectOrientedProgramming.OOPDemo.Services.UniversityService;
import ObjectOrientedProgramming.OOPDemo.Utils.MenuMessages;

import java.util.Scanner;

public class MainApp {


    static Menu menu = new Menu();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        UniversityService universityService = new UniversityService();
        DepartmentService departmentService = new DepartmentService();
        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();

        Boolean mainMenuContinue = true;
        while (mainMenuContinue) {
            menu.displayMenu();
            Integer option = input.nextInt();
            switch (option) {
                case 1 -> {
                    Boolean uniMenuContinue = true;
                    while (uniMenuContinue) {
                        System.out.println(" *********** University Menu ***********");
                        System.out.println(MenuMessages.UNIVERSITY_MENU_MESSAGE);
                        Integer universityOption = input.nextInt();
                        uniMenuContinue = universityService.handleUniversityMenu(universityOption);
                    }
                }
                case 2 -> {
                    Boolean uniMenuContinue = true;
                    while (uniMenuContinue) {
                        System.out.println(" *********** Department Menu ***********");
                        System.out.println(MenuMessages.DEPARTMENT_MENU_MESSAGE);
                        Integer departmentOption = input.nextInt();
                        uniMenuContinue = departmentService.handleDepartmentMenu(departmentOption);
                    }
                }
                case 3 -> {
                        System.out.println("Teacher Menu");
                    System.out.println("Teacher Menu");
                    Boolean teacherMenuContinue = true;
                    while (teacherMenuContinue) {
                        System.out.println(" *********** Teacher Menu ***********");
                        System.out.println(MenuMessages.TEACHER_MENU_MESSAGE);
                        Integer teachertOption = input.nextInt();

                        teacherMenuContinue = teacherService.handleTeacherMenu(teachertOption);}
                }
                case 4 ->{
                    System.out.println("Student Menu");
                    Boolean studentMenuContinue = true;
                    while (studentMenuContinue) {
                        System.out.println(" *********** Student Menu ***********");
                        System.out.println(MenuMessages.STUDENT_MENU_MESSAGE);
                        Integer studentOption = input.nextInt();
                        studentMenuContinue = studentService.handleStudentMenu(studentOption);}
                }

                case 5 -> System.out.println("Course Menu");
                case 6 -> {
                    System.out.println("Exit");
                    mainMenuContinue = false;
                }
                default -> System.out.println("Select a choice from the list");
            }
        }



        }
    }









