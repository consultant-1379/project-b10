package com.example.cloudnative.controllers;

import com.example.cloudnative.service.ResultService;
import com.example.cloudnative.view.GraphView;
import com.example.cloudnative.view.ResultView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/result")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping()
    public ResponseEntity<List<GraphView>> persist(@RequestBody ResultView resultView) {
        try{
            return ResponseEntity.ok().body(resultService.processResults(resultView));
        }catch (Exception e){
            return ResponseEntity.status(500).build();
        }

    }
}
