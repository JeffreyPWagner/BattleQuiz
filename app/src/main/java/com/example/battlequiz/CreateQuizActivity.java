package com.example.battlequiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateQuizActivity extends AppCompatActivity {

    public static int QUESTION_RESULT = 0;

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

        // instantiate add question button and set it to launch the create question activity
        // the create question activity will return a new question to add to the quiz
        addQuestionBut = (Button)findViewById(R.id.add_question_button);
        addQuestionBut.setOnClickListener((View v) -> {
            Intent addQuestionIntent = new Intent(CreateQuizActivity.this, CreateQuestionActivity.class);
            startActivityForResult(addQuestionIntent, QUESTION_RESULT);
        });
    }
}
