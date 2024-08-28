package com.example.cloudnative.view;



import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserIdAndQuestionsView {

    private int userId;

    private List<AreaView> listOfAreaView;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<AreaView> getListOfAreaView() {
        return listOfAreaView;
    }

    public void setListOfAreaView(List<AreaView> listOfAreaView) {
        this.listOfAreaView = listOfAreaView;
    }

    @Override
    public String toString() {
        return "UserIdAndQuestionsView{" +
                "userId=" + userId +
                ", listOfAreaView=" + listOfAreaView +
                '}';
    }
}
