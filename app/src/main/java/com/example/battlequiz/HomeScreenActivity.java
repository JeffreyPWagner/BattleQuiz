package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    // button to create quiz
    Button createQuizBut;
    public static int FINISH_CREATE_QUIZ = 1;

    // button to take quiz
    Button takeQuizBut;

    EditText quizNameInput;

    public static DatabaseReference topRef;
    List<Quiz> allQuizzes = new ArrayList<Quiz>();;
    HashMap<String, Integer> quizMap = new HashMap<>();



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
        takeQuizBut.setOnClickListener((View v) -> {
            if( quizMap.containsKey(quizNameInput.getText().toString()) ){
                Intent takeQuizIntent = new Intent(HomeScreenActivity.this, TakeQuizActivity.class);
                startActivity(takeQuizIntent);
            }
            else{
                quizNameInput.setText("");
                //Also display invalid quiz name in toolbar
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        topRef = FirebaseDatabase.getInstance().getReference("quizzes");
        topRef.addValueEventListener(valEvListener);

    }

    @Override
    public void onPause(){
        super.onPause();
        topRef.removeEventListener(valEvListener);
    }

    ValueEventListener valEvListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            Iterable<DataSnapshot> children = dataSnapshot.getChildren();
            int i = 0;
            allQuizzes.clear(); //This could be changed to add the last item
            for (DataSnapshot child: children) {
                Quiz tempQuiz = child.getValue(Quiz.class);
                allQuizzes.add(tempQuiz);
                quizMap.put(tempQuiz.getName(),i);
                i++;
            }
            Log.d("Val Event Listener","complete");
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            System.out.println("The read failed: " + databaseError.getCode());
        }
    };
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
