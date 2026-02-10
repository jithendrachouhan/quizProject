package com.jithendra.quizProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jithendra.quizProject.model.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Integer> {

}
