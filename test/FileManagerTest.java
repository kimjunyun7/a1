import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileManagerTest {

    FileManager fileManager = new FileManager();
    Enrolment enrolment = new Enrolment();
    private static final String CSV_SEPERATOR = ",";

    @Rule
    public TemporaryFolder testFolder = new TemporaryFolder();

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("Before class");
    }

    @Before
    public void setUpBeforeMethod() throws Exception {
        System.out.println("Before each method");
    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {
        System.out.println("After class");
    }

    @After
    public void setUpAfterMethod() throws Exception {
        System.out.println("After each method");
    }

    @Test
    public void loadFile() {
        fileManager.loadFile(Enrolment.file);
        List<StudentEnrolment> expected = new ArrayList<>();
        expected.add(new StudentEnrolment(new Student("S101312","Alex Mike","10/13/1998"),
                new Course("COSC4030","Theory of Computation","5"),"2020C"));
        expected.add(new StudentEnrolment(new Student("S102732","Mark Duong","8/28/2001"),
                new Course("COSC4030","Theory of Computation","5"),"2020C"));

        List<StudentEnrolment> actual = new ArrayList<>();
        actual.add(enrolment.studentEnrolmentList.get(0));
        actual.add(enrolment.studentEnrolmentList.get(1));

        assertEquals(expected.size(), actual.size());
        for (int i=0; i<actual.size(); i++) {
            Assert.assertEquals(expected.get(i).getStudent(), actual.get(i).getStudent());
            Assert.assertEquals(expected.get(i).getCourse(), actual.get(i).getCourse());
            Assert.assertEquals(expected.get(i).getSemester(), actual.get(i).getSemester());
        }
    }

    @Test
    public void writeFile() throws IOException  {
        final File tempFile = testFolder.newFile("tempFile.txt");
        final File tempFile2 = testFolder.newFile("tempFile2.txt");
        BufferedWriter writer = Files.newBufferedWriter(Paths.get(tempFile.toString()), StandardCharsets.UTF_8);
        StringBuffer oneLine = new StringBuffer();
        oneLine.append("S101312");
        oneLine.append(CSV_SEPERATOR);
        oneLine.append("Alex Mike");
        oneLine.append(CSV_SEPERATOR);
        oneLine.append("10/13/1998");
        oneLine.append(CSV_SEPERATOR);
        oneLine.append("COSC4030");
        oneLine.append(CSV_SEPERATOR);
        oneLine.append("Theory of Computation");
        oneLine.append(CSV_SEPERATOR);
        oneLine.append("5");
        oneLine.append(CSV_SEPERATOR);
        oneLine.append("2020C");
        writer.write(oneLine.toString());
        writer.newLine();
        writer.close();
        fileManager.writeFile(tempFile2.toString());
        Assert.assertEquals(tempFile, tempFile2);
    }
}