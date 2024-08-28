package com.example.cloudnative;


import com.example.cloudnative.models.Area;
import com.example.cloudnative.models.Question;
import com.example.cloudnative.models.User;
import com.example.cloudnative.repository.AreaRepository;
import com.example.cloudnative.repository.UserRepository;
import com.example.cloudnative.service.QuestionService;
import com.example.cloudnative.view.AreaView;
import com.example.cloudnative.view.UserIdAndQuestionsView;
import static org.hamcrest.CoreMatchers.is;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = {QuestionService.class})
class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @MockBean
    private UserIdAndQuestionsView userIdAndQuestionsView;

    @MockBean
    private AreaRepository areaRepository;

    @MockBean
    private UserRepository userRepository;


    @Test
    void verifyAllQuestionsAndUserIDAreFetched(){

        User user=new User();
        user.setUserId(100);
        user.setName(null);
        user.setCompany(null);
        user.setEmail(null);

        Area area=new Area(1,"Culture",4,null,null);

        List<Question> listOfQuestions = new ArrayList<>();

        Question question=new Question(1,"top level question",-1,area);
        listOfQuestions.add(question);

        question=new Question(2,"description",0,area);
        listOfQuestions.add(question);

        question=new Question(3,"sub question1",1,area);
        listOfQuestions.add(question);

        area.setListOfQuestion(listOfQuestions);

        List<Area> listOfArea =  new ArrayList<>();
        listOfArea.add(area);


        when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        when(areaRepository.findAll()).thenReturn(listOfArea);


        AreaView areaView=new AreaView();
        areaView.setAreaId(1);
        areaView.setNameOfArea("Culture");
        areaView.setTopLevelQuestion("top level question");
        areaView.setDescription("description");
        listOfQuestions = new ArrayList<>();
        listOfQuestions.add(question);
        areaView.setSubQuestions(listOfQuestions);

        List<AreaView> list=new ArrayList<>();
        list.add(areaView);

        userIdAndQuestionsView.setListOfAreaView(list);
        assertThat(questionService.getUserIdAndAllQuestionsAtStart().getUserId(),is(100));
        assertThat(questionService.getUserIdAndAllQuestionsAtStart().getListOfAreaView().size(),is(1));
    }
}
