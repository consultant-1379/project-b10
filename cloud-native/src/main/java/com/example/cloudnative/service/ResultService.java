package com.example.cloudnative.service;

import com.example.cloudnative.models.Area;
import com.example.cloudnative.models.AreaToStage;
import com.example.cloudnative.models.User;
import com.example.cloudnative.repository.AreaRepository;
import com.example.cloudnative.repository.UserRepository;
import com.example.cloudnative.view.Answer;
import com.example.cloudnative.view.GraphView;
import com.example.cloudnative.view.ResultView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ResultService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AreaRepository areaRepository;


    private static int start = 0;


    public List<GraphView> processResults(ResultView resultView){

        updateUserDetails(resultView.getUser());

        return constructGraph(resultView.getListOfAnswers());

    }

    private List<GraphView> constructGraph(List<Answer> listOfAnswer){

        List<GraphView> listOfGraphView = new ArrayList<>();
        int sizeOfAnswerList = listOfAnswer.size();
        AtomicInteger numberOfQuestions = new AtomicInteger();

        int incrementer = start;

        for(int index=incrementer; index<sizeOfAnswerList; index=incrementer){

            Answer answer = listOfAnswer.get(index);

            float xCoordinate = answer.getValue() ? MainStages.CLOUD_NATIVE.getyAxisValue() : getxCoordinate(listOfAnswer, index);
            Optional<Area> areaOptional = areaRepository.findById(answer.getAreaId());

            areaOptional.ifPresent(area -> {
                GraphView graphView = createAGraphViewObject(area, xCoordinate);
                listOfGraphView.add(graphView);
                numberOfQuestions.set(area.getNumberOfSubQuestions());
            });

            incrementer += answer.getValue() ? 1:numberOfQuestions.get()+1;

        }

        return  listOfGraphView;
    }

    private GraphView createAGraphViewObject(Area area, float xCoordinate) {

        GraphView graphView=new GraphView();

        String areaName = area.getAreaName();

        List<AreaToStage> listOfAreaToStage = area.getListOfStages();

        int descriptionId = (int)(Math.floor(xCoordinate))-1;

        graphView.setAreaName(areaName);
        graphView.setWordDescription(listOfAreaToStage.get(descriptionId).getWordDescription());
        graphView.setxCoordinate(xCoordinate);

        return graphView;
    }


    public float getxCoordinate(List<Answer> listOfAnswer, int i) {

        float xCoordinate;

        boolean waterfall1 = listOfAnswer.get(i+1).getValue();
        boolean agile1 = listOfAnswer.get(i+2).getValue();
        boolean waterfall2 = listOfAnswer.get(i+3).getValue();
        boolean agile2 = listOfAnswer.get(i+4).getValue();


        if((agile1 || agile2) && (waterfall1 || waterfall2)){
            xCoordinate = MainStages.BETWEEN_WATERFALL_AGILE.getyAxisValue();
        }else if(waterfall1 || waterfall2)
        {
            xCoordinate = MainStages.WATERFALL.getyAxisValue();
        }else if(agile1 || agile2){
            xCoordinate = MainStages.AGILE.getyAxisValue();
        }else{
            xCoordinate = MainStages.BETWEEN_WATERFALL_AGILE.getyAxisValue();
        }
        return xCoordinate;
    }

    private void updateUserDetails(User user){
        Optional<User> existingUser = userRepository.findById(user.getUserId());
        existingUser.ifPresent(userObject -> {
            BeanUtils.copyProperties(user, userObject);
            userRepository.save(userObject);
        });
    }
}
