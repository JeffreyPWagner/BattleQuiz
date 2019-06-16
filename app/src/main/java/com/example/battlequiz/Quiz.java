package com.example.battlequiz;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Quiz implements Parcelable {

    private String name;
    private List<Question> questions;

    public List<String> getHighScores() {
        return highScores;
    }

    private List<String> highScores;

    private String _key;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    private String userID;

    public String get_key() {
        return _key;
    }

    public Quiz() {
        this.name = null;
        this.questions = null;
        this._key = null;
        this.highScores = new ArrayList<>();
        this.userID = null;
    }

    public Quiz(String name, List<Question> questions, String _key, List<String> highScores, String userID) {
        this.name = name;
        this.questions = questions;
        this._key = _key;
        this.highScores = new ArrayList<>();
        this.userID = userID;
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

    public void addHighscore(String name, int score) {
        highScores.add(score +" "+ name);
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeTypedList(this.questions);
        dest.writeStringList(this.highScores);
        dest.writeString(this._key);
        dest.writeString(this.userID);
    }

    protected Quiz(android.os.Parcel in) {
        this.name = in.readString();
        this.questions = in.createTypedArrayList(Question.CREATOR);
        this.highScores = in.createStringArrayList();
        this._key = in.readString();
        this.userID = in.readString();
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(android.os.Parcel source) {
            return new Quiz(source);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };
}
