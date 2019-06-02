package com.example.battlequiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class CreateQuiz extends AppCompatActivity {

    // New quiz associated with this activity instance
    Quiz quiz;

    // Input for quiz name
    EditText quizNameInput;

    // Button to add a question to the quiz
    Button addQuestionBut;

    // Button to save the quiz
    Button finishQuixBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_quiz_activity);

        // Create a new quiz when activity launches
        quiz = new Quiz();
    }
}
