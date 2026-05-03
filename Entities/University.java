package ObjectOrientedProgramming.OOPDemo.Entities;

import ObjectOrientedProgramming.OOPDemo.Behaviours.DepartmentInterface;
import ObjectOrientedProgramming.OOPDemo.Behaviours.UniversityInterface;
import ObjectOrientedProgramming.OOPDemo.Services.UniversityService;

import java.util.ArrayList;
import java.util.List;

public class University extends ParentEntity implements UniversityInterface {

    private List<Department> departments = new ArrayList<>();

    private String address;

    private List<Student> studentList;

    private List<Teacher> teacherList;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {

        this.departments = departments;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public Boolean registerStudent(Student student) {
        Boolean status = studentList.add(student);
        return status;
    }


    @Override
    public void chargeFees(Integer studentId, String studentName) {
        System.out.println("Fee Charged");
    }

    @Override
    public Boolean hireTeachers(Teacher teacher) {
        Boolean status = teacherList.add(teacher);
        return status;
    }

    @Override
    public void displayDepartments() {
        System.out.println("University Name" + UniversityService.university.getName());
        for (Department department : UniversityService.university.getDepartments()) {
            System.out.println("Department Id: " + department.getId());
            System.out.println("Department Name: " + department.getName());
            for (Course c : department.getOfferedCourses()) {
                System.out.println("Course Id: " + c.getId());
                System.out.println("Course Name: " + c.getName());
                System.out.println("Course Code: " + c.getCourseCode());
            }
        }
    }

    public void displayStudents() {
        System.out.println("University Name: " + UniversityService.university.getName());

        for (Department department : UniversityService.university.getDepartments()) {

            System.out.println("Department Id: " + department.getId());
            System.out.println("Department Name: " + department.getName());

            for (Student student : department.getStudents()) {
                System.out.println("Student Id: " + student.getId());
                System.out.println("Student Name: " + student.getName());
            }
        }
    }

    public void displayTeachers() {
        System.out.println("University Name: " + UniversityService.university.getName());

        for (Department department : UniversityService.university.getDepartments()) {

            System.out.println("Department Id: " + department.getId());
            System.out.println("Department Name: " + department.getName());

            for (Teacher teacher : department.getTeachers()) {
                System.out.println("Teacher Id: " + teacher.getId());
                System.out.println("Teacher Name: " + teacher.getName());
            }
        }
    }

    public void displayCourses() {
        System.out.println("University Name: " + UniversityService.university.getName());

        for (Department department : UniversityService.university.getDepartments()) {

            System.out.println("Department Id: " + department.getId());
            System.out.println("Department Name: " + department.getName());

            for (Course course : department.getOfferedCourses()) {
                System.out.println("Course Id: " + course.getId());
                System.out.println("Course Name: " + course.getName());
                System.out.println("Course Code: " + course.getCourseCode());
            }
        }
    }

    @Override
    public String toString() {
        return "University{" +
                "departments=" + departments +
                ", address='" + address + '\'' +
                ", studentList=" + studentList +
                ", teacherList=" + teacherList +
                '}';
    }

    public void setTeacherList() {
    }
}