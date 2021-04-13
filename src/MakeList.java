import java.util.ArrayList;
import java.util.HashSet;

public class MakeList {

    private Enrolment enrolment = new Enrolment();

    // Make a list of students from a file
    public void makeStudentList() {
        HashSet<String> st = new HashSet<String>();
        enrolment.studentList = new ArrayList<Student>();
        for(StudentEnrolment studentE : enrolment.studentEnrolmentList)
            enrolment.studentList.add(studentE.getStudent());
        enrolment.studentList.removeIf(e->!st.add(e.getName()));
    }


    // Make a list of courses from a file
    public void makeCourseList() {
        HashSet<String> co = new HashSet<String>();
        enrolment.courseList = new ArrayList<Course>();
        for(StudentEnrolment studentE : enrolment.studentEnrolmentList)
            enrolment.courseList.add(studentE.getCourse());
        enrolment.courseList.removeIf(e->!co.add(e.getName()));
    }
}
