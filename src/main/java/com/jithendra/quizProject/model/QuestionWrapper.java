package com.jithendra.quizProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuestionWrapper {
    private Integer id;
    private String questionText;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
}
