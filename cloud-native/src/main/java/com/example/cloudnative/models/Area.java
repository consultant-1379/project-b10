package com.example.cloudnative.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int areaId;

    private String areaName;

    private int numberOfSubQuestions;

    @JsonIgnore
    @OneToMany(mappedBy="area")
    private List<AreaToStage> listOfStages;

    @JsonIgnore
    @OneToMany(mappedBy="area")
    private List<Question> listOfQuestion;



    public List<AreaToStage> getListOfStages() {
        return listOfStages;
    }

    public void setListOfStages(List<AreaToStage> listOfStages) {
        this.listOfStages = listOfStages;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<Question> getListOfQuestion() {
        return listOfQuestion;
    }

    public void setListOfQuestion(List<Question> listOfQuestion) {
        this.listOfQuestion = listOfQuestion;
    }

    public int getNumberOfSubQuestions() {
        return numberOfSubQuestions;
    }

    public void setNumberOfSubQuestions(int numberOfSubQuestions) {
        this.numberOfSubQuestions = numberOfSubQuestions;
    }

    public Area(int areaId, String areaName, int numberOfSubQuestions, List<AreaToStage> listOfStages, List<Question> listOfQuestion) {
        this.areaId = areaId;
        this.areaName = areaName;
        this.numberOfSubQuestions = numberOfSubQuestions;
        this.listOfStages = listOfStages;
        this.listOfQuestion = listOfQuestion;
    }
    public Area(){}

}
