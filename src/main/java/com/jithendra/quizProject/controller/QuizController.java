package com.jithendra.quizProject.controller;

import java.util.List;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jithendra.quizProject.model.AnswerModel;
import com.jithendra.quizProject.model.QuestionWrapper;
import com.jithendra.quizProject.model.Quiz;
import com.jithendra.quizProject.service.QuizService;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    @Autowired
    QuizService service;

    @GetMapping("/health")
    public String test() {
        return "Quiz Controller is working!";
    }

    @PostMapping("/createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam int size, @RequestParam String category, @RequestParam String difficulty ) {
        return service.createQuiz(title, size, category, difficulty);
    }

    @GetMapping("/getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id) {
        System.out.println("Received request for quiz with ID: " + id);
        return service.getQuizById(id);
    }


    @GetMapping("/submit/{id}")
    public ResponseEntity<String> getScore(@PathVariable int id, @RequestBody List<AnswerModel> answers){
        return service.getScore(id, answers);
    }

}
