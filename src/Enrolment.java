import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Enrolment implements StudentEnrolmentManager {

    public static ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<StudentEnrolment>();;
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static ArrayList<Course> courseList = new ArrayList<>();

    private static String file = "default.csv";
    private static String studentId = "";
    private static String courseId = "";
    private static String semester = "";

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

        // Menu
        System.out.println("%--- MENU ---%");

        // User chooses an option
        System.out.print("1. Add a new enrolment \n2. Update enrolments" +
                "\n3. Delete an enrolment \n4. Read enrolment list " +
                "\nEnter a number of an option: ");
        userInput = scanner.nextLine();
        enrolment.nextStep();
        switch (Integer.parseInt(userInput)) {

            // add a new enrolment
            case 1:
                while(true) {
                    System.out.println("Enter a student information..");
                    enrolment.enterStudentId();
                    enrolment.enterSemester();
                    enrolment.add();
                    System.out.print("Do you want to enter more? (yes / no): ");
                    userInput = scanner.nextLine().toLowerCase();
                    if(!userInput.isEmpty()) {
                        if(userInput.equals("no")) break;
                        else if (!userInput.equals("yes")) enrolment.printInvalid();
                    } else enrolment.printInvalid();
                }
                break;

            // Update enrolments
            case 2:
                enrolment.update(userInput);
                break;

            // Delete an enrolment
            case 3:
                enrolment.delete();
                break;

            // Read enrolment list
            case 4:
                while(true) {
                    System.out.print("1. All courses of 1 student in 1 semester \n" +
                            "2. All students of 1 course in 1 semester \n" +
                            "3. All courses in 1 semester \nEnter a number of an option: ");
                    userInput = scanner.nextLine();
                    enrolment.nextStep();
                    if(!(userInput.equals("")) || enrolment.isParsable(userInput)) {
                        // input == 1
                        if(userInput.equals("1")) {
                            enrolment.enterStudentId();
                            enrolment.enterSemester();
                        }
                        // input == 2
                        else if(userInput.equals("2")) {
                            enrolment.enterCourse();
                            enrolment.enterSemester();
                        }
                        // input == 3
                        else if(userInput.equals("3")) {
                            enrolment.enterSemester();
                        }
                        enrolment.getAll(Integer.parseInt(userInput));
                        break;
                    }
                }
                break;

            default:
                enrolment.printInvalid();
                break;
        }
    }

    public void enterStudentId() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Student ID: ");
            studentId = scanner.nextLine().toUpperCase();
            if (studentId.isEmpty()) printInvalid();
            else if(findStudent(studentId) == null) System.out.println("Unregistered Student!");
            else break;
        }
    }


    // Ask user to enter semester
    public void enterSemester() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Semester: ");
            semester = scanner.nextLine().toUpperCase();
            if (semester.isEmpty()) printInvalid();
            else break;
        }
    }


    // Ask user to enter course
    public void enterCourse() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Course: ");
            courseId = scanner.nextLine().toUpperCase();
            if (courseId.isEmpty()) printInvalid();
            else if(findCourse(courseId) == null) System.out.println("No Course Found!");
            else break;
        }
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


    // Check if a String input is parsable to an integer
    public boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
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
            //stCourse = scanner.nextLine().toUpperCase();
        }
        System.out.println();
    }

    @Override
    public void update(String input) {

    }

    @Override
    public void delete() {

    }

    @Override
    public StudentEnrolment getOne() {
        for (StudentEnrolment list : studentEnrolmentList) {
            if (list.getStudent().getId().equals(studentId) && list.getCourse().getId().equals(courseId)
                    && list.getSemester().equals(semester)) {
                System.out.println(list.getStudent().getId()+", "+list.getStudent().getName()+", "+
                        list.getStudent().getBirthdate()+" | "+
                        list.getCourse().getId()+", "+list.getCourse().getName()+", "+
                        list.getCourse().getNumOfCredit()+" | "+list.getSemester());
                return new StudentEnrolment(list.getStudent(), list.getCourse(), list.getSemester());
            }
        }
        return null;
    }

    @Override
    public void getAll(int input) {
        // All courses of 1 student in 1 semester
        if(input == 1) { // input == 4 when update enrolments
            System.out.print("studentID: " + studentId);
            System.out.println(" | Sem: " + semester);
            System.out.println("Courses: ");
            for (StudentEnrolment list : studentEnrolmentList) {
                if (list.getStudent().getId().equals(studentId) && list.getSemester().equals(semester))
                    System.out.println(list.getCourse().getId() + ", " + list.getCourse().getName() + ", " +
                            list.getCourse().getNumOfCredit());
            }
        }
        // All students of 1 course in 1 semester
        else if(input == 2) {
            System.out.print("Course: " + courseId);
            System.out.println(" | Sem: " + semester);
            System.out.println("Students: ");
            for (StudentEnrolment list : studentEnrolmentList) {
                if(list.getCourse().getId().equals(courseId) && list.getSemester().equals(semester))
                    System.out.println(list.getStudent().getId()+", "+list.getStudent().getName()+", "+
                            list.getStudent().getBirthdate());
            }
        }
        // All courses in 1 semester
        else if(input == 3) {
            System.out.println("Sem: " + semester);
            System.out.println("Courses: ");
            for (StudentEnrolment list : studentEnrolmentList) {
                if(list.getSemester().equals(semester))
                    System.out.println(list.getCourse().getId()+", "+list.getCourse().getName()+", "+
                            list.getCourse().getNumOfCredit());
            }
        }
        System.out.println();
    }
}
