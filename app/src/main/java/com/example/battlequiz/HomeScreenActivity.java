package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class HomeScreenActivity extends AppCompatActivity {

    // button to create quiz
    Button createQuizBut;

    // button to take quiz
    Button takeQuizBut;

    EditText quizNameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // generated java, may remove menu later
        setContentView(R.layout.home_screen_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        quizNameInput = findViewById(R.id.quiz_name_input_home);

        // instantiate create quiz button and set it to launch the create quiz activity
        createQuizBut = (Button)findViewById(R.id.create_quiz_but);
        createQuizBut.setOnClickListener((View v) -> {
            Intent createQuizIntent = new Intent(HomeScreenActivity.this, CreateQuizActivity.class);
            startActivity(createQuizIntent);
        });

        // instantiate take quiz button and set it to launch the take quiz activity
        takeQuizBut = (Button)findViewById(R.id.take_quiz_but);
        //listener changed for game testing
//        takeQuizBut.setOnClickListener((View v) -> {
//            Intent takeQuizIntent = new Intent(HomeScreenActivity.this, TakeQuizActivity.class);
//            startActivity(takeQuizIntent);
//        });
        takeQuizBut.setOnClickListener((View v) -> {
            Intent takeQuizIntent = new Intent(HomeScreenActivity.this, SpaceInvadersActivity.class);
            startActivity(takeQuizIntent);
        });
    }

    // generated java, may remove menu later
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // generated java, may remove menu later
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
