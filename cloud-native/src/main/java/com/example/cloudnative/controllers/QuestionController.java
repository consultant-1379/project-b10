package com.example.cloudnative.controllers;

import com.example.cloudnative.service.QuestionService;
import com.example.cloudnative.view.UserIdAndQuestionsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping(value = "/all")
    public ResponseEntity<UserIdAndQuestionsView> getUserIdAndAllQuestionsAtStart() {
        try
        {
            UserIdAndQuestionsView obj=questionService.getUserIdAndAllQuestionsAtStart();
            return ResponseEntity.ok().body(obj);
        }
        catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
}
