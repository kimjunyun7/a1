import java.time.LocalDate;
import java.util.List;

public class StudentEnrolment {

    private String semester;
    private Student student;
    private Course course;
//    List<Student> students;
//    List<Course> courses;

    StudentEnrolment(String semester, Student student, Course course) {
        this.semester = semester;
        this.student =student;
        this.course = course;
    }


    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}