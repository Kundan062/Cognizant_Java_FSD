import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exercise2_ParameterizedLogging {
    private static final Logger logger =
        LoggerFactory.getLogger(Exercise2_ParameterizedLogging.class);

    public static void main(String[] args) {
        String userName = "Vinay";
        int orderCount = 3;

        logger.info("User {} has {} pending orders", userName, orderCount);
        logger.warn("User {} attempted checkout with {} pending orders", userName, orderCount);
    }
}
