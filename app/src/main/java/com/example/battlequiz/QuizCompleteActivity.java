package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizCompleteActivity extends AppCompatActivity {

    TextView quizScore;
    Button startGameBut;

    int score;
    int numQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_complete_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        quizScore = findViewById(R.id.quiz_score);
        startGameBut = findViewById(R.id.start_game_but);

        Intent scoreIntent = getIntent();
        score = scoreIntent.getIntExtra("score", 0);
        numQuestions = scoreIntent.getIntExtra("numQuestions", 0);

        quizScore.setText("Score: " + score + "/" + numQuestions);

        startGameBut.setOnClickListener((View v) -> {
            Intent startGameIntent = new Intent(QuizCompleteActivity.this, SpaceInvadersActivity.class);
            startGameIntent.putExtra("gameLives", score);
            startActivity(startGameIntent);
        });
    }

}
