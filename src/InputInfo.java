import java.util.Scanner;

public class InputInfo {

    private Enrolment enrolment = new Enrolment();
    private FindFromList findFromList = new FindFromList();

    // Ask user to enter student id
    public String enterStudentId() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Student ID: ");
            String studentId = scanner.nextLine().toUpperCase();
            if (studentId.isEmpty()) enrolment.printInvalid();
            else if(findFromList.findStudent(studentId) == null)
                System.out.println("Unregistered Student!");
            else return studentId;
        }
    }


    // Ask user to enter semester
    public String enterSemester() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Semester: ");
            String semester = scanner.nextLine().toUpperCase();
            if (semester.isEmpty()) enrolment.printInvalid();
            else return semester;
        }
    }


    // Ask user to enter course
    public String enterCourse() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Course: ");
            String courseId = scanner.nextLine().toUpperCase();
            if (courseId.isEmpty()) enrolment.printInvalid();
            else if(findFromList.findCourse(courseId) == null)
                System.out.println("No Course Found!");
            else return courseId;
        }
    }
}
