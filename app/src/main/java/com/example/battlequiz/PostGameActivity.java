package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

public class PostGameActivity extends AppCompatActivity {

    int score;
    EditText nameInput;
    Quiz quiz;
    TextView scoreView;
    Button saveScoreBut;
    Button shareScoreBut;
    RecyclerView leaderboard;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_game_activity);

        nameInput = findViewById(R.id.name_input);

        saveScoreBut = findViewById(R.id.save_score_but);
        saveScoreBut.setOnClickListener((View v) ->{
            quiz.addHighscore(nameInput.getText().toString(),score);
            HomeScreenActivity.topRef.child(quiz.get_key()).setValue(quiz);
            finish();
        });

        shareScoreBut = findViewById(R.id.share_score_but);
        shareScoreBut.setOnClickListener((View v)->{
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);

            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "I scored " + Integer.toString(score) + " on the " + quiz.getName() + " quiz!");
            startActivity(Intent.createChooser(shareIntent, "Share your Score"));

        });

        scoreView = findViewById(R.id.score_view);
        leaderboard = findViewById(R.id.leadboard_post_game);

        Intent postGameIntent = getIntent();
        score = postGameIntent.getIntExtra("score", 0);
        scoreView.setText(Integer.toString(score));
        Parcelable quizPar = postGameIntent.getParcelableExtra("quiz");
        quiz = Parcels.unwrap(quizPar);

        leaderboard.setHasFixedSize(true);
        leaderboard.setLayoutManager(new LinearLayoutManager(this));
        if (quiz.getHighScores() != null) {
            mAdapter = new LeaderAdapter(quiz.getHighScores());
        }
        else {
            mAdapter = new LeaderAdapter(new ArrayList<String>());
        }
        leaderboard.setAdapter(mAdapter);

    }

}
