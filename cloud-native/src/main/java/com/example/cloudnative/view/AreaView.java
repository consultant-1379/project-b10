package com.example.cloudnative.view;

import com.example.cloudnative.models.Question;

import java.util.List;

public class AreaView {

    private int areaId;
    private String nameOfArea;
    private String topLevelQuestion;
    private String description;
    private List<Question> subQuestions;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getNameOfArea() {
        return nameOfArea;
    }

    public void setNameOfArea(String nameOfArea) {
        this.nameOfArea = nameOfArea;
    }

    public String getTopLevelQuestion() {
        return topLevelQuestion;
    }

    public void setTopLevelQuestion(String topLevelQuestion) {
        this.topLevelQuestion = topLevelQuestion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getSubQuestions() {
        return subQuestions;
    }

    public void setSubQuestions(List<Question> subQuestions) {
        this.subQuestions = subQuestions;
    }

    @Override
    public String toString() {
        return "AreaView{" +
                "areaId=" + areaId +
                ", nameOfArea='" + nameOfArea + '\'' +
                ", topLevelQuestion='" + topLevelQuestion + '\'' +
                ", description='" + description + '\'' +
                ", subQuestions=" + subQuestions +
                '}';
    }
}
