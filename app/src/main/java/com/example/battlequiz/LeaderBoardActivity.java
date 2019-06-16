package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.parceler.Parcels;

public class LeaderBoardActivity extends AppCompatActivity {

    Quiz quiz;
    RecyclerView leaderboard;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leader_board_activity);

        Intent leaderboardIntent = getIntent();
        Parcelable quizPar = leaderboardIntent.getParcelableExtra("quiz");
        quiz = Parcels.unwrap(quizPar);



        leaderboard = findViewById(R.id.leader_board);

        leaderboard.setHasFixedSize(true);
        leaderboard.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new LeaderAdapter(quiz.getHighScores());
        leaderboard.setAdapter(mAdapter);
    }
}
