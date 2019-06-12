package com.example.battlequiz;

import android.os.Parcelable;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Quiz implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeTypedList(this.questions);
        dest.writeString(this._key);
    }

    protected Quiz(android.os.Parcel in) {
        this.name = in.readString();
        this.questions = in.createTypedArrayList(Question.CREATOR);
        this._key = in.readString();
    }

    public static final Parcelable.Creator<Quiz> CREATOR = new Parcelable.Creator<Quiz>() {
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
