package com.example.battlequiz;

import java.util.ArrayList;

public class Quiz {

    private String name = "Quiz";
    private ArrayList<Question> questions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }
}
