package adrian;

import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoggerDemoTest {

    @Captor
    private ArgumentCaptor<LogEvent> logCaptor;

    @Mock
    private Appender appender;

    private LoggerDemo demo;

    private Logger logger;

    @Before
    public void setup() {
        when(appender.getName()).thenReturn("MockAppender");
        when(appender.isStarted()).thenReturn(true);

        // casting from org.apache.logging.log4j.Logger to org.apache.logging.log4j.core.Logger
        logger = (Logger) LogManager.getLogger(LoggerDemo.class);
        logger.addAppender(appender);
        logger.setLevel(Level.INFO);
        demo = new LoggerDemo();
    }

    @After
    public void tearDown() {
        // the appender we added will sit in the singleton logger forever
        // slowing future things down - so remove it
        logger.removeAppender(appender);
    }

    @Test
    public void test() {
        demo.userLogger();
        verifyErrorMessages(appender, logCaptor, "Hi, I am here");
    }

    // handy function to inspect the messages sent to the logger
    static void verifyErrorMessages(Appender appender,
                                    ArgumentCaptor<LogEvent> logCaptor,
                                    String ... messages) {
        verify(appender, times(messages.length)).append(logCaptor.capture());

        int i=0;
        for(LogEvent loggingEvent:logCaptor.getAllValues()) {
            assertEquals(messages[i++], loggingEvent.getMessage().getFormattedMessage());
        }
    }
}

