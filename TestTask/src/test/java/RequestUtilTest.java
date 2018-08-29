import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import parser.Parser;
import solution.RequestUtil;

public class RequestUtilTest {
    private Parser parser;
    private RequestUtil requestUtil;
    public static final String[] exampleTimes = new String[]{"83", "100", " - "};

    @Before
    public void initialize() {
        parser = new Parser();
        requestUtil = new RequestUtil();
    }

    @Test
    public void averageTimesTest() {
        parser.parse();
        String[] times = requestUtil.findAverageTimes(parser.getWaitingTimeLines(), parser.getQueries());

        for (int i = 0; i < exampleTimes.length; i++) {
            Assert.assertEquals(exampleTimes[i], times[i]);
        }
    }

    @After
    public void destroy() {
        parser = null;
        requestUtil = null;
    }
}
