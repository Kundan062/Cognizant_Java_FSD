import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise3_UsingDifferentAppenders {
    private static final Logger logger =
        LoggerFactory.getLogger(Exercise3_UsingDifferentAppenders.class);

    public static void main(String[] args) {
        logger.debug("Debug message written through configured appenders");
        logger.info("Info message written to console and file appenders");
        logger.error("Error message written to all root appenders");
    }
}
