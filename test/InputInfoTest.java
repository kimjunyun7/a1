import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class InputInfoTest {

    InputInfo inputInfo = new InputInfo();

    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

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

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void enterStudentId() {
        final String input = "S101312";
        provideInput(input);

        String studentId = inputInfo.enterStudentId();

        assertEquals("S101312", studentId);
    }

    @Test
    public void enterSemester() {
    }

    @Test
    public void enterCourse() {
    }
}