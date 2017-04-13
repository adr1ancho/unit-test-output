package adrian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDemo {
    private static final Logger LOG = LoggerFactory.getLogger(LoggerDemo.class);

    public void userLogger() {
        LOG.info("Hi, I am {}", "here");
    }
}
