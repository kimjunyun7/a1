import java.util.*;

public class Enrolment implements StudentEnrolmentManager {

    public ArrayList<StudentEnrolment> studentEnrolmentList = new ArrayList<StudentEnrolment>();
    public ArrayList<Student> studentList = new ArrayList<>();
    public ArrayList<Course> courseList = new ArrayList<>();

    public static String file = "default.csv";
    private static String studentId = "";
    private static String courseId = "";
    private static String semester = "";

    private static InputInfo inputInfo = new InputInfo();
    private static FindFromList findFromList = new FindFromList();


    public static void main(String[] args) throws Exception {

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
                    studentId = inputInfo.enterStudentId();
                    semester = inputInfo.enterSemester();
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
                            studentId = inputInfo.enterStudentId();
                            semester = inputInfo.enterSemester();
                        }
                        // input == 2
                        else if(userInput.equals("2")) {
                            courseId = inputInfo.enterCourse();
                            semester = inputInfo.enterSemester();
                        }
                        // input == 3
                        else if(userInput.equals("3")) {
                            semester = inputInfo.enterSemester();
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

        enrolment.nextStep();
        System.out.println("Result..");
        System.out.println();
        for (StudentEnrolment list : enrolment.studentEnrolmentList) {
            System.out.println(list.getStudent().getId()+", "+list.getStudent().getName()+", "+
                    list.getStudent().getBirthdate()+" | "+
                    list.getCourse().getId()+", "+list.getCourse().getName()+", "+
                    list.getCourse().getNumOfCredit()+" | "+list.getSemester());

        }
        fileManager.writeFile(file);
    }


    // Print a bar to separate each step of the system
    public void nextStep() {
        System.out.println();
        System.out.println("------------------------------");
        System.out.println();
    }


    // Print "Invalid" when input has errors
    public void printInvalid() {
        System.out.println("Invalid input, try again!");
        System.out.println();
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
        while(true) {
            try {
                System.out.println("Number of courses to enroll: ");
                numOfCourses = Integer.parseInt(scanner.nextLine());
                break;
            } catch (InputMismatchException e) {
                printInvalid();
            }
        }
        System.out.println("Enter "+numOfCourses+" course IDs.. ");
        for (int i=0; i<numOfCourses; i++) {
            courseId = inputInfo.enterCourse();
            studentEnrolmentList.add(
                    new StudentEnrolment(findFromList.findStudent(studentId),
                            findFromList.findCourse(courseId), semester));
        }
        System.out.println();
    }

    @Override
    public void update(String input) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Enter a student information to update..");
            studentId = inputInfo.enterStudentId();
            semester = inputInfo.enterSemester();
            System.out.println();
            getAll(1);
            System.out.print("Do you want add or delete a course? (add / delete): ");
            input = scanner.nextLine().toLowerCase();
            if(input.equals("add") || input.equals("delete")) {
                if(input.equals("add")) add();
                if(input.equals("delete")) delete();
            } else {
                printInvalid();
                continue;
            }
            break;
        }
    }

    @Override
    public void delete() {
        while(true) {
            System.out.println();
            getAll(1);
            System.out.println("Enter enrolment details to delete..");
            studentId = inputInfo.enterStudentId();
            courseId = inputInfo.enterCourse();
            semester = inputInfo.enterSemester();
            StudentEnrolment se = getOne();
            if (se != null) {
                studentEnrolmentList.remove(se);
                break;
            }
        }
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