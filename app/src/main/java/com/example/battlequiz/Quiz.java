package com.example.battlequiz;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Quiz {

    private String name;
    private List<Question> questions;

    private String _key;

    public String get_key() {
        return _key;
    }

    public Quiz() {
        this.name = null;
        this.questions = null;
        this._key = null;
    }

    public Quiz(String name, List<Question> questions, String _key) {
        this.name = name;
        this.questions = questions;
        this._key = _key;
    }

    public void set_key(String _key) {
        this._key = _key;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
