import java.time.LocalDate;
import java.util.List;

public class StudentEnrolment {

    private Student student;
    private Course course;
    private String semester;


    StudentEnrolment(Student student, Course course, String semester) {
        this.student = student;
        this.course = course;
        this.semester = semester;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        StudentEnrolment e = (StudentEnrolment) obj;
        return this.getStudent() == (e.getStudent())
                && this.getCourse() == (e.getCourse())
                && this.getSemester() == (e.getSemester());
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

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}

