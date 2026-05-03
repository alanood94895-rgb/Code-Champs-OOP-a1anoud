package ObjectOrientedProgramming.OOPDemo.Services;

import ObjectOrientedProgramming.OOPDemo.Entities.Department;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class DepartmentService {

    CourseService courseService = new CourseService();
    Department department = new Department();

    List<Department> departmentList = new ArrayList<>();
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
        List<Department> departmentList = new ArrayList<>();
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

    public void updateDepartment(List<Department> departmentList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Department List:");
        UniversityService.university.displayDepartments();

        System.out.println("Enter Department Name to Update:");
        String updateDepartment = scanner.nextLine();

        for (Department department : departmentList) {
            if (department.getName().equalsIgnoreCase(updateDepartment)) {

                System.out.println("Enter new Department Name:");
                String newName = scanner.nextLine();
                department.setName(newName);
                System.out.println(Constants.DEPARTMENT_UPDATED_SUCCESSFULLY);
            }
        }
    }

    public void deleteDepartment(List<Department> departmentList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Department List:");
        UniversityService.university.displayDepartments();

        System.out.println("Enter Department Name to Delete:");
        String deleteDepartment = scanner.nextLine();

        boolean removed = false;

        for (int i = 0; i < departmentList.size(); i++) {
            if (departmentList.get(i).getName().equalsIgnoreCase(deleteDepartment)) {

                departmentList.remove(i);

                System.out.println(Constants.DEPARTMENT_DELETED_SUCCESSFULLY);
                return;

            }
        }
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

    public Boolean handleDepartmentMenu(Integer departmentOption) {

        switch (departmentOption) {
            case 1 -> {
                System.out.println("Add new Department");
                departmentList.add(addNewDepartment());
            }

            case 2 -> {
                System.out.println("Update Department");
                updateDepartment(departmentList);
            }

            case 3 -> {
                System.out.println("Show Departments");

            }

            case 4 -> {
                System.out.println("Delete Department");
                deleteDepartment(departmentList);
            }

            case 5 -> {
                return false;
            }
        }
        return true;
    }
}




