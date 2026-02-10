package com.jithendra.quizProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jithendra.quizProject.model.Questions;

@Repository
public interface QuestionRepo extends JpaRepository<Questions,Integer>{
    // @Query("SELECT q FROM Questions q WHERE LOWER(q.category) = LOWER(:category)")
    List<Questions> findAllByCategory(String category);

    @Query(value = "SELECT * FROM Questions q WHERE q.category = :category AND q.difficulty_level = :difficultyLevel ORDER BY RANDOM() LIMIT :size", nativeQuery = true)
    List<Questions> findByCategoryAndDifficultyLevel(
            String category, String difficultyLevel, int size);
}
