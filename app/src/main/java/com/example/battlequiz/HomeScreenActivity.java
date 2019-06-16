package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

public class HomeScreenActivity extends AppCompatActivity {

    // button to create quiz
    Button createQuizBut;
    public static int FINISH_CREATE_QUIZ = 1;
    public static String userID;

    // button to take quiz
    Button takeQuizBut;
    Button editQuizBut;
    Button leaderboardBut;
    Button loginBut;
    Button signUpBut;
    Button signOutBut;

    EditText quizNameInput;

    public static DatabaseReference topRef;
    List<Quiz> allQuizzes = new ArrayList<Quiz>();;
    HashMap<String, Integer> quizMap = new HashMap<>();

    public static FirebaseAuth mAuth;
    public static FirebaseUser currentUser = null;

    public Twitter twitter = TwitterFactory.getSingleton();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen_activity);

        mAuth = FirebaseAuth.getInstance();



        signOutBut = findViewById(R.id.signout_but);
        signOutBut.setOnClickListener((View v)->{
            mAuth.signOut();
            currentUser = null;
            userID = null;
            onResume();
        });

        signUpBut = findViewById(R.id.homescreen_signup_but);
        signUpBut.setOnClickListener((View v) ->{
            Intent signUpIntent = new Intent(HomeScreenActivity.this, SignUpActivity.class);
            startActivity(signUpIntent);
        });


        quizNameInput = findViewById(R.id.quiz_name_input_home);

        loginBut = findViewById(R.id.homescreen_login_but);
        loginBut.setOnClickListener((View v)->{
            Intent loginIntent = new Intent(HomeScreenActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });

        // instantiate create quiz button and set it to launch the create quiz activity
        createQuizBut = (Button)findViewById(R.id.create_quiz_but);
        createQuizBut.setOnClickListener((View v) -> {
            Intent createQuizIntent = new Intent(HomeScreenActivity.this, CreateQuizActivity.class);
            startActivity(createQuizIntent);

        });

        // instantiate take quiz button and set it to launch the take quiz activity
        takeQuizBut = (Button)findViewById(R.id.take_quiz_but);
        takeQuizBut.setOnClickListener((View v) -> {

            String quizName = quizNameInput.getText().toString();
            if( quizMap.containsKey(quizName) ){
                Quiz quiz = allQuizzes.get(quizMap.get(quizName));
                Intent takeQuizIntent = new Intent(HomeScreenActivity.this, TakeQuizActivity.class);
                Parcelable quizParcel = Parcels.wrap(quiz);
                takeQuizIntent.putExtra("quiz", quizParcel);
                startActivity(takeQuizIntent);
            }
            else{
                quizNameInput.setText("");
                //Also display invalid quiz name in toolbar
            }
        });

        editQuizBut = (Button) findViewById(R.id.edit_quiz_but);
        editQuizBut.setOnClickListener((View v) ->{
            String quizName = quizNameInput.getText().toString();
            if( quizMap.containsKey(quizName) ){
                Quiz quiz = allQuizzes.get(quizMap.get(quizName));
                if(!quiz.getUserID().equals(userID) ){
                    quizNameInput.setText("");
                    Toast.makeText(HomeScreenActivity.this, "You cannot edit a quiz you did not create.",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent editQuizIntent = new Intent(HomeScreenActivity.this, CreateQuizActivity.class);
                    Parcelable quizParcel = Parcels.wrap(quiz);
                    editQuizIntent.putExtra("quiz", quizParcel);
                    startActivity(editQuizIntent);
                }
            }
            else{
                quizNameInput.setText("");
                //Also display invalid quiz name in toolbar
            }
        });

        leaderboardBut = findViewById(R.id.leaderboard_but);
        leaderboardBut.setOnClickListener((View v) ->{
            String quizName = quizNameInput.getText().toString();
            if( quizMap.containsKey(quizName)  ){
                Quiz quiz = allQuizzes.get(quizMap.get(quizName));


                Intent leaderboardIntent = new Intent(HomeScreenActivity.this, LeaderBoardActivity.class);
                Parcelable quizParcel = Parcels.wrap(quiz);
                leaderboardIntent.putExtra("quiz", quizParcel);
                startActivity(leaderboardIntent);
            }
            else{
                quizNameInput.setText("");
                //Also display invalid quiz name in toolbar
            }
        });
    }


    @Override
    public void onStart(){
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        currentUser = mAuth.getCurrentUser();
        onResume();
    }



    @Override
    public void onResume(){
        super.onResume();

        if (currentUser != null) {
            // Name, email address, and profile photo Url
            String name = currentUser.getDisplayName();
            loginBut.setVisibility(View.INVISIBLE);
            signUpBut.setVisibility(View.INVISIBLE);
            signOutBut.setVisibility(View.VISIBLE);
            editQuizBut.setVisibility(View.VISIBLE);
            createQuizBut.setVisibility(View.VISIBLE);
            userID = currentUser.getUid();
            //Logout Button Visable
        }
        else {
            loginBut.setVisibility(View.VISIBLE);
            signUpBut.setVisibility(View.VISIBLE);
            editQuizBut.setVisibility(View.INVISIBLE);
            createQuizBut.setVisibility(View.INVISIBLE);
            signOutBut.setVisibility(View.INVISIBLE);
            userID = null;
            //log out button invisable
        }
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
                tempQuiz.set_key(child.getKey());
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
}
