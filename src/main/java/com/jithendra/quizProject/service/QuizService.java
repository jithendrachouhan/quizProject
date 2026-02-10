package com.jithendra.quizProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jithendra.quizProject.model.AnswerModel;
import com.jithendra.quizProject.model.QuestionWrapper;
import com.jithendra.quizProject.model.Questions;
import com.jithendra.quizProject.model.Quiz;
import com.jithendra.quizProject.repository.QuestionRepo;
import com.jithendra.quizProject.repository.QuizRepo;

@Service
public class QuizService {

    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    QuizRepo quizRepo;

    public ResponseEntity<String> createQuiz(String title, int size, String category, String difficulty) {
        List<Questions> questions = questionRepo.findByCategoryAndDifficultyLevel(category, difficulty, size);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizRepo.save(quiz);
        return new ResponseEntity<>("Quiz created with " + questions.size() + " questions!", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
        System.out.println("Service received request for quiz with ID: " + id);
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isPresent()) {
            List<QuestionWrapper> questionWrappers = quiz.get().getQuestions().stream()
                    .map(q -> new QuestionWrapper(q.getId(), q.getQuestionText(), q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()))
                    .toList();
            return new ResponseEntity<>(questionWrappers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<String> getScore(int id, List<AnswerModel> answers) {
        Optional<Quiz> quiz = quizRepo.findById(id);
        int resultScore = 0;
        if(quiz.isPresent()){
            for(int i = 0; i< answers.size(); i++){
                if(quiz.get().getQuestions().get(i).getCorrectAnswer().equals(answers.get(i).getAnswer()))
                resultScore++;
            }
            return new ResponseEntity<>("You have scored " + resultScore + " Out of " + answers.size(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
