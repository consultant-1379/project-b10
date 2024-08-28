package com.example.cloudnative.view;

public class Answer {

    private int areaId;
    private int questionId;
    private boolean value;

    public Answer(int areaId, boolean value) {
        this.areaId = areaId;
        this.value = value;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "areaId=" + areaId +
                ", questionId=" + questionId +
                ", value=" + value +
                '}';
    }
}
