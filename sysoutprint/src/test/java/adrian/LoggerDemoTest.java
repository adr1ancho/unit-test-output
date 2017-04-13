package adrian;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class LoggerDemoTest {

    private static final String LINE_SEPARATOR = "line.separator";
    private LoggerDemo demo;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        demo = new LoggerDemo();
    }

    @After
    public void afterTest() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void test() {
        demo.userLogger();
        assertThat(outContent.toString(), is("Hi, I am here" + System.getProperty(LINE_SEPARATOR)));
    }

}

