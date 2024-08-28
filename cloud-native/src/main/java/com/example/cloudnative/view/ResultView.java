package com.example.cloudnative.view;

import com.example.cloudnative.models.User;

import java.util.List;

public class ResultView {


    private User user;
    private List<Answer> listOfAnswers;

    public List<Answer> getListOfAnswers() {
        return listOfAnswers;
    }

    public void setListOfAnswers(List<Answer> listOfAnswers) {
        this.listOfAnswers = listOfAnswers;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ResultView{" +
                "user=" + user +
                ", listOfAnswers=" + listOfAnswers +
                '}';
    }
}
