package entities;

import types.ResponseType;

public abstract class Common {
    protected int serviceId;
    protected int variationId;
    protected int questionTypeId;
    protected int categoryId;
    protected int subCategoryId;
    protected ResponseType responseType;

    public Common() {
        serviceId = 0;
        variationId = 0;
        questionTypeId = 0;
        categoryId = 0;
        subCategoryId = 0;
        responseType = null;
    }

    public Common(int serviceId, int variationId, int questionTypeId, int categoryId, int subCategoryId,
                  ResponseType responseType) {
        this.serviceId = serviceId;
        this.variationId = variationId;
        this.questionTypeId = questionTypeId;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.responseType = responseType;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getVariationId() {
        return variationId;
    }

    public void setVariationId(int variationId) {
        this.variationId = variationId;
    }

    public int getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(int questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    protected String printServiceInf() {
        StringBuilder stringBuilder = new StringBuilder();

        if (serviceId == 0) {
            return "*";
        } else {
            stringBuilder.append(serviceId);

            if (variationId != 0) {
                stringBuilder.append(".").append(variationId);
            }
        }

        return stringBuilder.toString();
    }

    protected String printQuestionInf() {
        StringBuilder stringBuilder = new StringBuilder();

        if (questionTypeId == 0) {
            return "*";
        } else {
            stringBuilder.append(questionTypeId);

            if (categoryId != 0) {
                stringBuilder.append(".").append(categoryId);

                if (subCategoryId != 0) {
                    stringBuilder.append(".").append(subCategoryId);
                }
            }
        }

        return stringBuilder.toString();
    }
}
