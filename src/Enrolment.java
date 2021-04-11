import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Enrolment implements StudentEnrolmentManager {

    private static ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<StudentEnrolment>();;
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static ArrayList<Course> courseList = new ArrayList<>();

    public static void main(String[] args) {
        Enrolment enrolment = new Enrolment();
        enrolment.add();
    }

    // Find a specific course object
    private Course findCourse(String courseId) {
        for(Course course : courseList) {
            if(course.getId().equals(courseId))
            return course;
        }
        return null;
    }


    // Find a specific student object
    private Student findStudent(String stId) {
        for(Student student : studentList) {
            if(student.getId().equals(stId))
                return student;
        }
        return null;
    }

    @Override
    public void add() {
        int numOfCourses = 0;
        String stCourse;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a student information..");
        System.out.print("Student ID: ");
        String stId = scanner.nextLine().toUpperCase(); // Student ID
        System.out.print("Semester: ");
        String stSem = scanner.nextLine().toUpperCase(); // Semester student will enroll
        while(true) {
            try {
                System.out.println("Number of courses to enroll: ");
                numOfCourses = scanner.nextInt();
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input!");
            }
        }
        System.out.println("Enter "+numOfCourses+" courses: ");
        for (int i=0; i<numOfCourses; i++) {
            System.out.println("Course "+(i+1));
            stCourse = scanner.nextLine().toUpperCase();
            studentEnrolmentList.add(
                    new StudentEnrolment(stSem, findStudent(stId),
                            findCourse(stCourse)));
        }
        System.out.println();
    }

    @Override
    public void update() {

    }

    @Override
    public void delete() {

    }

    @Override
    public void getOne() {

    }

    @Override
    public void getAll() {

    }
}
