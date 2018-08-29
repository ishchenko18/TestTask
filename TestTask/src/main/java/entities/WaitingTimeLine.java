package entities;

import types.ResponseType;

import java.time.LocalDate;

public class WaitingTimeLine extends Common {
    private LocalDate date;
    private int timeMinutes;

    public WaitingTimeLine() {

    }

    public WaitingTimeLine(int serviceId, int variationId, int questionTypeId, int categoryId, int subCategoryId,
                           ResponseType responseType, LocalDate date, int time) {
        super(serviceId, variationId, questionTypeId, categoryId, subCategoryId, responseType);
        this.date = date;
        this.timeMinutes = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTimeMinutes() {
        return timeMinutes;
    }

    public void setTimeMinutes(int timeMinutes) {
        this.timeMinutes = timeMinutes;
    }

    @Override
    public String toString() {
        return "C " + printServiceInf() + " " + printQuestionInf() +
                " " + responseType + " " + date + " " + timeMinutes;
    }
}
