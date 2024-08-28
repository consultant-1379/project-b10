package com.example.cloudnative;

import com.example.cloudnative.models.Area;
import com.example.cloudnative.models.AreaToStage;
import com.example.cloudnative.models.User;
import com.example.cloudnative.repository.AreaRepository;
import com.example.cloudnative.repository.UserRepository;
import com.example.cloudnative.service.ResultService;
import com.example.cloudnative.view.Answer;
import com.example.cloudnative.view.ResultView;
import static org.hamcrest.MatcherAssert.assertThat;


import static org.mockito.Mockito.when;


import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(classes = {ResultService.class})
class ResultServiceTest {

    private User user;
    private Area[] arrayOfAreaObjects;

    @Autowired
    private ResultService resultService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private AreaRepository areaRepository;

    @BeforeEach
    public void init(){
        user = getUser();
        arrayOfAreaObjects = getAreas();

        when(userRepository.findById(Mockito.any(Integer.class))).thenReturn(Optional.of(user));
        when(userRepository.save(Mockito.any(User.class))).thenAnswer(i -> i.getArguments()[0]);
        when(areaRepository.findById(Mockito.any(Integer.class))).thenAnswer(i -> {
            int index = ((int)i.getArguments()[0])-1;
            return Optional.of(arrayOfAreaObjects[index]);
        });
    }

    @Test
    void verify_That_ProcessResult_Produces_An_Agile_condition_1(){

        ResultView resultView = getResultView(false,true,false,true); // verify Agile works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,3}));
    }
    @Test
    void verify_That_ProcessResult_Produces_An_Agile_condition_2(){

        ResultView resultView = getResultView(false,true,false,false); // verify Agile works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,3}));
    }
    @Test
    void verify_That_ProcessResult_Produces_An_Agile_condition_3(){

        ResultView resultView = getResultView(false,false,false,true); // verify Agile works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,3}));
    }

    @Test
    void verify_That_ProcessResult_Produces_A_Waterfall_condition_1(){

        ResultView resultView = getResultView(true,false,true,false); // verify Waterfall works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,2}));
    }

    @Test
    void verify_That_ProcessResult_Produces_A_Waterfall_condition_2(){

        ResultView resultView = getResultView(true,false,false,false); // verify Waterfall works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,2}));
    }
    @Test
    void verify_That_ProcessResult_Produces_A_Waterfall_condition_3(){

        ResultView resultView = getResultView(false,false,true,false); // verify Waterfall works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,2}));
    }

    @Test
    void verify_That_ProcessResult_Produces_A_Condition_Between_Waterfall_and_Agile_1(){

        ResultView resultView = getResultView(false,false,false,false); // verify between Waterfall and Agile works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,2.5f}));

    }
    @Test
    void verify_That_ProcessResult_Produces_A_Condition_Between_Waterfall_and_Agile_2(){

        ResultView resultView = getResultView(true,true,false,false); // verify between Waterfall and Agile works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,2.5f}));
    }
    @Test
    void verify_That_ProcessResult_Produces_A_Condition_Between_Waterfall_and_Agile_3(){

        ResultView resultView = getResultView(true,false,false,true); // verify between Waterfall and Agile works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,2.5f}));
    }

    @Test
    void verify_That_ProcessResult_Produces_A_Condition_Between_Waterfall_and_Agile_4(){

        ResultView resultView = getResultView(true,true,true,true); // verify between Waterfall and Agile works
        assertThat(resultService.processResults(resultView), MyMatchers.matchTheXCoordinates(new float[]{4,2.5f}));

    }

    private ResultView getResultView(boolean... answerValues) {
        ResultView resultView=new ResultView();

        User user=new User();
        user.setUserId(5);
        user.setCompany("XYZ");
        user.setEmail("UVW");
        user.setName("ABC");
        resultView.setUser(user);
        List<Answer> listOfAnswer = getAnswers(answerValues);

        resultView.setListOfAnswers(listOfAnswer);
        return resultView;
    }

    private List<Answer> getAnswers(boolean... answerValues) {
        List<Answer> listOfAnswer = new ArrayList<>();

        Answer answer=new Answer(1,true);
        listOfAnswer.add(answer);

        answer=new Answer(2,false);
        listOfAnswer.add(answer);

        answer=new Answer(2,answerValues[0]);
        listOfAnswer.add(answer);

        answer=new Answer(2,answerValues[1]);
        listOfAnswer.add(answer);

        answer=new Answer(2,answerValues[2]);
        listOfAnswer.add(answer);

        answer=new Answer(2,answerValues[3]);
        listOfAnswer.add(answer);
        return listOfAnswer;
    }


    private Area[] getAreas() {
        Area[] arrayOfAreaObjects = { new Area(1, "Culture", 4, null,null),
                                      new Area(2, "Prod-service design", 4, null,null) };

        List<AreaToStage> listOfAreaToStages=new ArrayList<>();
        AreaToStage areaToStage=new AreaToStage("Individualist",arrayOfAreaObjects[0]);
        listOfAreaToStages.add(areaToStage);
        areaToStage=new AreaToStage("Predictive",arrayOfAreaObjects[0]);
        listOfAreaToStages.add(areaToStage);
        areaToStage=new AreaToStage("Iterative",arrayOfAreaObjects[0]);
        listOfAreaToStages.add(areaToStage);
        areaToStage=new AreaToStage("Collaborative",arrayOfAreaObjects[0]);
        listOfAreaToStages.add(areaToStage);
        areaToStage=new AreaToStage("Experimental",arrayOfAreaObjects[0]);
        listOfAreaToStages.add(areaToStage);
        arrayOfAreaObjects[0].setListOfStages(listOfAreaToStages);

        listOfAreaToStages.clear();
        areaToStage=new AreaToStage("Arbitary",arrayOfAreaObjects[1]);
        listOfAreaToStages.add(areaToStage);
        areaToStage=new AreaToStage("Long-term plan",arrayOfAreaObjects[1]);
        listOfAreaToStages.add(areaToStage);
        areaToStage=new AreaToStage("Feature driven",arrayOfAreaObjects[1]);
        listOfAreaToStages.add(areaToStage);
        areaToStage=new AreaToStage("Data driven",arrayOfAreaObjects[1]);
        listOfAreaToStages.add(areaToStage);
        areaToStage=new AreaToStage("All driven",arrayOfAreaObjects[1]);
        listOfAreaToStages.add(areaToStage);
        arrayOfAreaObjects[1].setListOfStages(listOfAreaToStages);

        return arrayOfAreaObjects;
    }

    private User getUser() {
        User user=new User();
        user.setUserId(100);
        user.setEmail(null);
        user.setCompany(null);
        user.setName(null);
        return user;
    }

}
