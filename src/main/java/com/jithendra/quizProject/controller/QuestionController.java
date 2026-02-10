package com.jithendra.quizProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jithendra.quizProject.model.Questions;
import com.jithendra.quizProject.service.QuestionService;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;  

    @RequestMapping("/health")
    public String test() {
        return "Question Controller is working!";
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> postMethodName(@RequestBody Questions question) {
        return questionService.addQuestion(question);
    }
    
    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Questions>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/getQuestions/{category}")
    public ResponseEntity<List<Questions>> getQuestionById(@PathVariable String category) {
        return questionService.getQuestionsByCategory(category);
    }
}
