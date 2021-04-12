import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Enrolment implements StudentEnrolmentManager {

    public static ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<StudentEnrolment>();;
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static ArrayList<Course> courseList = new ArrayList<>();

    private static String file = "default.csv";

    public static void main(String[] args) {
        Enrolment enrolment = new Enrolment();
        FileManager fileManager = new FileManager();
        String userInput = "";
        Scanner scanner = new Scanner(System.in);

        System.out.println("%--- Student Enrolment System ---%");


        // Ask whether the user wants to load the enrolments from any file or not
        while (true) {
            System.out.print("Do you want to load a file? (yes / no): ");
            userInput = scanner.nextLine().toLowerCase();
            if (userInput.equals("yes")) {
                System.out.print("Enter directory of a file you want to load: ");
                file = scanner.nextLine();
                break;
            } else if(userInput.equals("no")) {
                break;
            } else {
                enrolment.printInvalid();
            }
        }
        fileManager.loadFile(file);
        enrolment.nextStep();
    }


    // Print a bar to separate each step of the system
    private void nextStep() {
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
    }


    // Print "Invalid" when input has errors
    private void printInvalid() {
        System.out.println("Invalid input, try again!");
        System.out.println();
    }


    // Make a list of students from a file
    public static void makeStudentList() {
        HashSet<String> st = new HashSet<String>();
        studentList = new ArrayList<Student>();
        for(StudentEnrolment studentE : studentEnrolmentList) studentList.add(studentE.getStudent());
        studentList.removeIf(e->!st.add(e.getName()));
    }


    // Make a list of courses from a file
    public static void makeCourseList() {
        HashSet<String> co = new HashSet<String>();
        courseList = new ArrayList<Course>();
        for(StudentEnrolment studentE : studentEnrolmentList) courseList.add(studentE.getCourse());
        courseList.removeIf(e->!co.add(e.getName()));
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
