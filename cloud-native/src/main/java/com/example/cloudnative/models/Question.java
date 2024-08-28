package com.example.cloudnative.models;


import javax.persistence.*;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    private String name;
    private int questionNumber;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    public Question(int questionId, String name, int questionNumber, Area area) {
        this.questionId = questionId;
        this.name = name;
        this.questionNumber = questionNumber;
        this.area = area;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }


    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", name='" + name + '\'' +
                ", questionNumber=" + questionNumber;
    }

}
