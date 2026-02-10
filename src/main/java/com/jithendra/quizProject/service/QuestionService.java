package com.jithendra.quizProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jithendra.quizProject.model.Questions;
import com.jithendra.quizProject.repository.QuestionRepo;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repository;

    public ResponseEntity<List<Questions>> getAllQuestions() {
        List<Questions> questions = repository.findAll();
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<String> addQuestion(Questions question) {
        repository.save(question);
        return new ResponseEntity<>("Question added successfully!",HttpStatus.CREATED);
    }

    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {
        System.out.println("Service received category: " + category);
        List<Questions> questions = repository.findAllByCategory(category);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

}
