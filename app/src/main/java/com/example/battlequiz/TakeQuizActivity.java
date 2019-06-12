package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

public class TakeQuizActivity extends AppCompatActivity {

    Quiz quiz;

    int quizScore;
    int currentQuestion;
    List<Question> questions;

    TextView questionTextView;
    Button choice1But;
    Button choice2But;
    Button choice3But;
    Button choice4But;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_quiz_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        questionTextView = findViewById(R.id.question_text_view);
        choice1But = findViewById(R.id.choice_1_but);
        choice2But = findViewById(R.id.choice_2_but);
        choice3But = findViewById(R.id.choice_3_but);
        choice4But = findViewById(R.id.choice_4_but);


        Intent quizIntent = getIntent();
        Parcelable quizPar = quizIntent.getParcelableExtra("quiz");
        quiz = Parcels.unwrap(quizPar);

        questions = quiz.getQuestions();

        currentQuestion = 0;

        loadQuestion();

        choice1But.setOnClickListener((View v) -> {
            if (questions.get(currentQuestion).getAnswerIndex() == 0) {
                quizScore++;
            }

            if (currentQuestion + 1 < questions.size()){
                currentQuestion++;
                loadQuestion();
            }
            else {
                finishQuiz(quizScore, questions.size());
            }
        });

        choice2But.setOnClickListener((View v) -> {
            if (questions.get(currentQuestion).getAnswerIndex() == 1) {
                quizScore++;
            }

            if (currentQuestion + 1 < questions.size()){
                currentQuestion++;
                loadQuestion();
            }
            else {
                finishQuiz(quizScore, questions.size());
            }
        });

        choice3But.setOnClickListener((View v) -> {
            if (questions.get(currentQuestion).getAnswerIndex() == 2) {
                quizScore++;
            }

            if (currentQuestion + 1 < questions.size()){
                currentQuestion++;
                loadQuestion();
            }
            else {
                finishQuiz(quizScore, questions.size());
            }
        });

        choice4But.setOnClickListener((View v) -> {
            if (questions.get(currentQuestion).getAnswerIndex() == 3) {
                quizScore++;
            }

            if (currentQuestion + 1 < questions.size()){
                currentQuestion++;
                loadQuestion();
            }
            else {
                finishQuiz(quizScore, questions.size());
            }
        });
    }

    private void loadQuestion(){
        questionTextView.setText(questions.get(currentQuestion).getQuestionText());
        choice1But.setText(questions.get(currentQuestion).getChoices().get(0));
        choice2But.setText(questions.get(currentQuestion).getChoices().get(1));
        choice3But.setText(questions.get(currentQuestion).getChoices().get(2));
        choice4But.setText(questions.get(currentQuestion).getChoices().get(3));
    }

    private void finishQuiz(int score, int numQuestions) {
        Intent completeQuizIntent = new Intent(TakeQuizActivity.this, QuizCompleteActivity.class);
        completeQuizIntent.putExtra("score", score);
        completeQuizIntent.putExtra("numQuestions", numQuestions);
        startActivity(completeQuizIntent);
    }

}
