package ObjectOrientedProgramming.OOPDemo.Entities;

import ObjectOrientedProgramming.OOPDemo.Behaviours.DepartmentInterface;
import ObjectOrientedProgramming.OOPDemo.Utils.Constants;

import java.util.List;
import java.util.UUID;

public class Department extends ParentEntity implements DepartmentInterface {

    List<Course> offeredCourses;

    public List<Course> getOfferedCourses() {
        return offeredCourses;
    }

    public void setOfferedCourses(List<Course> offeredCourses) {
        this.offeredCourses = offeredCourses;
    }

    @Override
    public Boolean addCourse(Course course) {

        Course courseToAdd = findCourseById(course.getId());
        if (!courseToAdd.getId().equals(null)) {
            System.out.println(Constants.COURSE_ALREADY_EXISTS);
            return false;
        }
        Boolean status = offeredCourses.add(course);
        System.out.println(Constants.COURSE_ADDED_SUCCESSFULLY);
        return status;
    }

    @Override
    public Boolean removeCourse(Course course) {
        Boolean status = offeredCourses.remove(course);
        System.out.println(status.equals(true) ? Constants.COURSE_DELETED_SUCCESSFULLY : Constants.COURSE_DELETE_FAILED);
        return status;
    }

    @Override
    public Boolean modifyCourse(UUID id, Course updatedCourse) {
        Course courseToRemove = findCourseById(id);
        if (!courseToRemove.getId().equals(null)) {
            offeredCourses.remove(courseToRemove);
            if (updatedCourse.getId().equals(courseToRemove.getId())) {
                offeredCourses.add(updatedCourse);
                System.out.println(Constants.COURSE_UPDATED_SUCCESSFULLY);
                return true;
            }
        }
        System.out.println(Constants.COURSE_UPDATE_FAILED);
        return false;
    }

    public Course findCourseById(UUID courseId) {
        for (Course c : offeredCourses) {
            if (c.getId().equals(courseId)) {
                return c;
            }
        }
        System.out.println(Constants.COURSE_NOT_FOUND);
        return new Course();
    }

    @Override
    public String toString() {
        return "Department{" +
                "offeredCourses=" + offeredCourses +
                '}';
    }

    public Student[] getStudents() {
        return new Student[0];
    }

    public Teacher[] getTeachers() {
        return new Teacher[0];
    }
}
