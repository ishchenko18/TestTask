import parser.Parser;
import solution.RequestUtil;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] times;
        Parser parser = new Parser();
        parser.parse();

        RequestUtil requestUtil = new RequestUtil();
        times = requestUtil.findAverageTimes(parser.getWaitingTimeLines(), parser.getQueries());

        Arrays.stream(times).forEach(System.out::println);
    }
}
