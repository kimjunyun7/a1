import java.util.ArrayList;

public class FindFromList {

    private Enrolment enrolment = new Enrolment();

    // Find a specific course object
    public Course findCourse(String courseId) {
        for(Course course : enrolment.courseList) {
            if(course.getId().equals(courseId))
                return course;
        }
        return null;
    }


    // Find a specific student object
    public Student findStudent(String stId) {
        for(Student student : enrolment.studentList) {
            if(student.getId().equals(stId))
                return student;
        }
        return null;
    }
}
