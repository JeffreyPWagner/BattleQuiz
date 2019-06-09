package com.example.battlequiz;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Question implements Parcelable {

    private String questionText;
    private ArrayList<String> choices;
    private int answerIndex;

    public Question(String questionText, String c1, String c2, String c3, String c4, int answerIndex) {
        choices = new ArrayList<>();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.questionText);
        dest.writeStringList(this.choices);
        dest.writeInt(this.answerIndex);
    }

    protected Question(Parcel in) {
        this.questionText = in.readString();
        this.choices = in.createStringArrayList();
        this.answerIndex = in.readInt();
    }

    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
