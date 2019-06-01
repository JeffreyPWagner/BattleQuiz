package com.example.battlequiz;

import java.util.ArrayList;

public class Question {

    private String questionText;
    private ArrayList<String> choices;
    private int answerIndex;

    public Question(String questionText, String c1, String c2, String c3, String c4, int answerIndex) {
        this.questionText = questionText;
        choices.add(c1);
        choices.add(c2);
        choices.add(c3);
        choices.add(c4);
        this.answerIndex = answerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public int getAnswerIndex() {
        return answerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        this.answerIndex = answerIndex;
    }
}
