package adrian;

import ch.qos.logback.core.Appender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatcher;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ch.qos.logback.classic.spi.LoggingEvent;

@RunWith(MockitoJUnitRunner.class)
public class LoggerDemoTest {

    @Captor
    private ArgumentCaptor<LoggingEvent> logCaptor;

    @Mock
    private Appender appender;

    private LoggerDemo demo;

    @Before
    public void setup() {
        ch.qos.logback.classic.Logger root = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(
                ch.qos.logback.classic.Logger.ROOT_LOGGER_NAME);
        root.addAppender(appender);
        demo = new LoggerDemo();
    }

    @Test
    public void test() {
        demo.userLogger();

        verify(appender).doAppend(argThat(new ArgumentMatcher() {
            @Override
            public boolean matches(final Object argument) {
                return ((LoggingEvent) argument).getFormattedMessage()
                        .contains("Hi, I am here");
            }
        }));
    }

}

