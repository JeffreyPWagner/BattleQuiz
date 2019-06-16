package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.parceler.Parcels;

public class QuizCompleteActivity extends AppCompatActivity {

    TextView quizScore;
    Button startGameBut;

    Quiz quiz;

    int score;
    int numQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_complete_activity);

        quizScore = findViewById(R.id.quiz_score);
        startGameBut = findViewById(R.id.start_game_but);

        Intent scoreIntent = getIntent();
        score = scoreIntent.getIntExtra("score", 0);
        Parcelable quizPar = scoreIntent.getParcelableExtra("quiz");
        quiz = Parcels.unwrap(quizPar);
        numQuestions = scoreIntent.getIntExtra("numQuestions", 0);

        quizScore.setText("Score: " + score + "/" + numQuestions);

        startGameBut.setOnClickListener((View v) -> {
            Intent startGameIntent = new Intent(QuizCompleteActivity.this, SpaceInvadersActivity.class);
            startGameIntent.putExtra("gameLives", score);
            Parcelable quizParcel = Parcels.wrap(quiz);
            startGameIntent.putExtra("quiz", quizParcel);
            startActivity(startGameIntent);
            finish();
        });
    }

}
