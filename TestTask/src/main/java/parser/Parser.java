package parser;

import entities.Common;
import entities.Query;
import entities.WaitingTimeLine;
import types.ResponseType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {
    /**
     * List of time lines
     */
    private ArrayList<WaitingTimeLine> waitingTimeLines;

    /**
     * list of queries
     */
    private ArrayList<Query> queries;

    public Parser() {
        waitingTimeLines = new ArrayList<>();
        queries = new ArrayList<>();
    }

    public ArrayList<WaitingTimeLine> getWaitingTimeLines() {
        return waitingTimeLines;
    }

    public void setWaitingTimeLines(ArrayList<WaitingTimeLine> waitingTimeLines) {
        this.waitingTimeLines = waitingTimeLines;
    }

    public ArrayList<Query> getQueries() {
        return queries;
    }

    public void setQueries(ArrayList<Query> queries) {
        this.queries = queries;
    }

    /**
     * Method, which read data from input file
     * @return array of strings, which read from file
     */
    private String[] readFromFile() {
        String[] lines = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get("src/main/resources/input.txt").toFile()))) {
            int length = Integer.parseInt(reader.readLine());
            int i = 0;
            String line;
            lines = new String[length];

            while ((line = reader.readLine()) != null) {
                lines[i++] = line.trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * Method, which parse input strings
     */
    public void parse() {
        for (String line : readFromFile()) {
            if (line.startsWith("C")) {
                waitingTimeLines.add(parseWaitingTimeLine(line));
            } else {
                queries.add(parseQuery(line));
            }
        }
    }

    /**
     * Method parse time line string
     * @param line - input string
     * @return object of WaitingTimeLine
     */
    private WaitingTimeLine parseWaitingTimeLine(String line) {
        String[] allInformation = line.split(" ");
        WaitingTimeLine timeLine = new WaitingTimeLine();

        for (int i = 1; i < allInformation.length; i++) {
            switch (i) {
                case 1:
                    parseServiceInformation(timeLine, allInformation[i]);
                    break;
                case 2:
                    parseQuestionInformation(timeLine, allInformation[i]);
                    break;
                case 3:
                    timeLine.setResponseType(ResponseType.valueOf(allInformation[i]));
                    break;
                case 4:
                    timeLine.setDate(parseDate(allInformation[i]));
                    break;
                case 5:
                    timeLine.setTimeMinutes(Integer.parseInt(allInformation[i]));
                    break;
            }
        }

        return timeLine;
    }

    /**
     * Method parse date
     * @param date - input date
     * @return object of LocalDate
     */
    private LocalDate parseDate(String date) {
        LocalDate localDate;

        String[] partOfDate = date.split("\\.");
        localDate = LocalDate.of(Integer.parseInt(partOfDate[2]), Integer.parseInt(partOfDate[1]), Integer.parseInt(partOfDate[0]));

        return localDate;
    }

    /**
     * Method, which parse string of query
     * @param line - string of query
     * @return object of Query
     */
    private Query parseQuery(String line) {
        String[] allInformation = line.split(" ");
        Query query = new Query();

        for (int i = 1; i < allInformation.length; i++) {
            switch (i) {
                case 1:
                    parseServiceInformationOfQuery(query, allInformation[i]);
                    break;
                case 2:
                    parseQuestionInformationOfQuery(query, allInformation[i]);
                    break;
                case 3:
                    query.setResponseType(ResponseType.valueOf(allInformation[i]));
                    break;
                case 4:
                    parseDatesOfQuery(query, allInformation[i]);
                    break;
            }
        }

        return query;
    }

    /**
     * Method, which parse information about type of service in query
     * @param query - query
     * @param line - string with information about type of service
     */
    private void parseServiceInformationOfQuery(Query query, String line) {
        if (line.length() == 1 && line.equals("*")) {
            query.setServiceId(0);
            query.setVariationId(0);
        } else {
            parseServiceInformation(query, line);
        }
    }

    /**
     * Method, which parse information about type of question in query
     * @param query - query
     * @param line - string with information about type of question
     */
    private void parseQuestionInformationOfQuery(Query query, String line) {
        if (line.length() == 1 && line.equals("*")) {
            query.setQuestionTypeId(0);
            query.setCategoryId(0);
            query.setSubCategoryId(0);
        } else {
            parseQuestionInformation(query, line);
        }
    }

    /**
     * Method, which parse dates from query
     * @param query - query
     * @param period - string of dates
     */
    private void parseDatesOfQuery(Query query, String period) {
        if (period.contains("-")) {
            String[] dates = period.split("-");

            query.setDateStart(parseDate(dates[0]));
            query.setDateEnd(parseDate(dates[1]));
        } else {
            LocalDate date = parseDate(period);
            query.setDateStart(date);
            query.setDateEnd(date);
        }
    }

    /**
     * Method, which parse information about service
     * @param common - object, which will contains parsed data
     * @param line - information about service
     */
    private void parseServiceInformation(Common common, String line) {
        String[] service = line.split("\\.");

        for (int j = 0; j < service.length; j++) {
            switch (j) {
                case 0:
                    common.setServiceId(Integer.parseInt(service[j]));
                    break;
                case 1:
                    common.setVariationId(Integer.parseInt(service[j]));
                    break;
            }
        }
    }

    /**
     * Method, which parse information about question
     * @param common - object, which will contains parsed data
     * @param line - information about question
     */
    private void parseQuestionInformation(Common common, String line) {
        String[] question = line.split("\\.");

        for (int j = 0; j < question.length; j++) {
            switch (j) {
                case 0:
                    common.setQuestionTypeId(Integer.parseInt(question[j]));
                    break;
                case 1:
                    common.setCategoryId(Integer.parseInt(question[j]));
                    break;
                case 2:
                    common.setSubCategoryId(Integer.parseInt(question[j]));
                    break;
            }
        }
    }
}
