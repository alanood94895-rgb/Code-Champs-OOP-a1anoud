package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Course;
import ObjectOrientedProgramming.OOPDemo.Entities.Department;
import ObjectOrientedProgramming.OOPDemo.Entities.University;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DepartmentService {

    CourseService courseService = new CourseService();
    University university = new University();

    public List<Department> getDepartments() {
        if (UniversityService.university.getDepartments() == null) {
            UniversityService.university.setDepartments(new ArrayList<>());
        }
        return UniversityService.university.getDepartments();
    }


    public Department addNewDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("** Adding new department **");

        Department department = new Department();
        department.setId(UUID.randomUUID());

        System.out.println("Enter Department Name");
        String deptName = scanner.nextLine();
        department.setName(deptName);

        department.setOfferedCourses(courseService.addNewCourses());

        return department;
    }

    public List<Department> addNewDepartments() {
        Scanner scanner = new Scanner(System.in);
        List<Department> departmentList = getDepartments();
        Boolean continueFlag = true;
        while (continueFlag) {
            //System.out.println("Entering multiple departments");
            departmentList.add(addNewDepartment());
            System.out.println(Constants.INPUT_EXIT_CONTINUE_MESSAGE_DEPARTMENTS);
            if (scanner.nextLine().equalsIgnoreCase("q")) {
                continueFlag = false;
            }
        }
        return departmentList;
    }

    public Department updateDepartment() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter department name to update:");
        String name = scanner.nextLine();

        Department existingDept = findDepartmentByName(name);
        if (existingDept == null) {
            System.out.println(Constants.DEPARTMENT_NOT_FOUND);
            return null;
        }

        System.out.println("Enter new department name:");
        String newName = scanner.nextLine();

        Department updatedDept = new Department();
        updatedDept.setId(existingDept.getId());
        updatedDept.setName(newName);
        updatedDept.setOfferedCourses(courseService.addNewCourses());

        modifyDepartment(name, updatedDept);
        return updatedDept;
    }

    public Boolean modifyDepartment(String departmentName, Department updatedDepartment) {
        Department existingDept = findDepartmentByName(departmentName);

        if (!existingDept.getId().equals(null)) {
            existingDept.setName(updatedDepartment.getName());
            existingDept.setOfferedCourses(updatedDepartment.getOfferedCourses());
            System.out.println(Constants.DEPARTMENT_UPDATED_SUCCESSFULLY);
            return true;
        }

        System.out.println(Constants.DEPARTMENT_UPDATED_FAILED);
        return false;
    }

    public Boolean deleteDepartment(){
        System.out.println("Enter Department to Remove: ");
        Scanner scanner = new Scanner(System.in);
        Department departmentToRemove = findDepartmentByName(scanner.nextLine());
        Boolean status = getDepartments().remove(departmentToRemove);
        System.out.println(status.equals(true) ? Constants.DEPARTMENT_DELETED_SUCCESSFULLY : Constants.DEPARTMENT_DELETED_FAILED);
        return status;


    }

    public Department findDepartmentByName(String departmentName) {
        for(Department d : getDepartments()){
            if(d.getName().equalsIgnoreCase(departmentName)){
                return d;
            }
        }
        return null;
    }

    public void displayDepartmentByName(){
        System.out.println("Enter Department Name: ");
        Scanner scanner = new Scanner(System.in);
        Department department = findDepartmentByName(scanner.nextLine());
        System.out.println("University Name" + UniversityService.university.getName());
        System.out.println("Department Id: " + department.getId());
        System.out.println("Department Name: " + department.getName());
        for (Course c : department.getOfferedCourses()) {
            System.out.println("Course Id: " + c.getId());
            System.out.println("Course Name: " + c.getName());
            System.out.println("Course Code: " + c.getCourseCode());
        }
    }




    public Boolean handleDepartmentMenu(Integer departmentOption){
        switch (departmentOption){
            case 1-> {
                addNewDepartments();
            }
            case 2->{
                updateDepartment();
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