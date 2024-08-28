package com.example.cloudnative.service;

import com.example.cloudnative.models.Area;
import com.example.cloudnative.models.Question;
import com.example.cloudnative.models.User;
import com.example.cloudnative.repository.AreaRepository;
import com.example.cloudnative.repository.UserRepository;
import com.example.cloudnative.view.AreaView;
import com.example.cloudnative.view.UserIdAndQuestionsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AreaRepository areaRepository;


    public UserIdAndQuestionsView getUserIdAndAllQuestionsAtStart() {

        // creating a new user
        User newUser = getANewUserId();

        // fetching all areas (along with questions) from database
        List<Area> listOfArea = areaRepository.findAll();

        return convertToViewObjects(newUser, listOfArea);

    }

    private UserIdAndQuestionsView convertToViewObjects(User newUser, List<Area> listOfArea) {

        UserIdAndQuestionsView userIdAndQuestionsView = new UserIdAndQuestionsView();

        userIdAndQuestionsView.setUserId(newUser.getUserId());

        List<AreaView> listOfAreaView = listOfArea.stream()
                .map(QuestionService::constructAreaViewObjectFromAreaObject)
                .collect(Collectors.toList());
        userIdAndQuestionsView.setListOfAreaView(listOfAreaView);

        return userIdAndQuestionsView;

    }

    private User getANewUserId() {

        User newUser = new User();

        newUser.setCompany(null);
        newUser.setEmail(null);
        newUser.setName(null);

        return userRepository.save(newUser);
    }

    private static AreaView constructAreaViewObjectFromAreaObject(Area areaObject) {

        AreaView areaView = new AreaView();

        areaView.setAreaId(areaObject.getAreaId());
        areaView.setNameOfArea(areaObject.getAreaName());


        List<Question> subQuestions = areaObject.getListOfQuestion().stream()
                .map(question -> {
                    String name = question.getName();

                    int questionNumber = question.getQuestionNumber();

                    if (questionNumber == -1) {
                        areaView.setTopLevelQuestion(name);
                    } else if (questionNumber == 0) {
                        areaView.setDescription(name);
                    }

                    return question;
                })
                .filter(question -> question.getQuestionNumber() > 0)
                .collect(Collectors.toList());


        areaView.setSubQuestions(subQuestions);

        return areaView;
    }
}
