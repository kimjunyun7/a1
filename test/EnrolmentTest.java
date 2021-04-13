import org.junit.*;

import static org.junit.Assert.*;

public class EnrolmentTest {

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
    public void main() {
    }

    @Test
    public void nextStep() {
    }

    @Test
    public void printInvalid() {
    }

    @Test
    public void isParsable() {
    }

    @Test
    public void add() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void getOne() {
    }

    @Test
    public void getAll() {
    }
}