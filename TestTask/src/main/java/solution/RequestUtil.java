package solution;

import entities.Query;
import entities.WaitingTimeLine;

import java.util.ArrayList;

public class RequestUtil {
    /**
     * Method, which find average time of waiting
     * @param timeLines - list of time lines
     * @param queries - list of queries
     * @return array with results
     */
    public String[] findAverageTimes(ArrayList<WaitingTimeLine> timeLines, ArrayList<Query> queries) {
        String[] times = new String[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            int count = 0;
            int time = 0;

            for (WaitingTimeLine timeLine : timeLines) {
                if (queries.get(i).isSuchRespond(timeLine)) {
                    count++;
                    time += timeLine.getTimeMinutes();
                }
            }

            if(count == 0) {
                times[i] = " - ";
            } else {
                times[i] = "" + Math.round(time / (double) count);
            }
        }

        return times;
    }
}
