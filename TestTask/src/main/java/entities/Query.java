package entities;

import types.ResponseType;

import java.time.LocalDate;

public class Query extends Common {
    private LocalDate dateStart;
    private LocalDate dateEnd;

    public Query() {

    }

    public Query(int serviceId, int variationId, int questionTypeId, int categoryId, int subCategoryId,
                 ResponseType responseType, LocalDate dateStart, LocalDate dateEnd) {
        super(serviceId, variationId, questionTypeId, categoryId, subCategoryId, responseType);
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isSuchRespond(WaitingTimeLine timeLine) {
        if (!dateStart.isAfter(timeLine.getDate()) && !dateEnd.isBefore(timeLine.getDate())
                && responseType.equals(timeLine.getResponseType())) {
            boolean flag;

            if (printQuestionInf().equals("*")) {
                flag = true;
            } else if (questionTypeId == timeLine.getQuestionTypeId() && (categoryId == 0 || categoryId == timeLine.getCategoryId())
                       && (subCategoryId == 0 || subCategoryId == timeLine.getSubCategoryId())) {
                flag = true;
            } else {
                return false;
            }

            if(printServiceInf().equals("*")) {
                flag = true;
            } else if(serviceId == timeLine.getServiceId() && (variationId == 0 || variationId == timeLine.getVariationId())) {
                flag = true;
            } else {
                return false;
            }

            return flag;
        }

        return false;
    }

    @Override
    public String toString() {
        return "D " + printServiceInf() + " " + printQuestionInf() +
                " " + responseType + " " + dateStart + " - " + dateEnd;
    }
}
