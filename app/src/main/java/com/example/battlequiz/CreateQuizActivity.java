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

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.UUID;

public class CreateQuizActivity extends AppCompatActivity {

    public static int QUESTION_RESULT = 0;

    // New quiz associated with this activity instance
    private Quiz quiz;

    ArrayList<Question> questions;

    // Input for quiz name
    EditText quizNameInput;

    // Button to add a question to the quiz
    Button addQuestionBut;

    // Button to save the quiz
    Button finishQuizBut;

    // List of questions
    RecyclerView questionList;
    RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_quiz_activity);

        // Create a new quiz when activity launches
        quiz = new Quiz();

        questions = new ArrayList<Question>();

        quizNameInput = findViewById(R.id.quiz_name_input);

        finishQuizBut = findViewById(R.id.finish_quiz_but);
        finishQuizBut.setOnClickListener((View v)-> {
            Intent quizIntent = new Intent();
            quiz.setQuestions(questions);
            quiz.setName(quizNameInput.getText().toString());
            quiz.set_key(UUID.randomUUID().toString());
            setResult(HomeScreenActivity.FINISH_CREATE_QUIZ, quizIntent);
            HomeScreenActivity.topRef.push().setValue(quiz);
            finish();
        });
        questionList = findViewById(R.id.question_list);

        // instantiate add question button and set it to launch the create question activity
        // the create question activity will return a new question to add to the quiz
        addQuestionBut = (Button)findViewById(R.id.add_question_button);
        addQuestionBut.setOnClickListener((View v) -> {
            Intent addQuestionIntent = new Intent(CreateQuizActivity.this, CreateQuestionActivity.class);
            startActivityForResult(addQuestionIntent, QUESTION_RESULT);
        });

        questionList.setHasFixedSize(true);
        questionList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new QuestionAdapter(questions);
        questionList.setAdapter(mAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == QUESTION_RESULT) {
            Parcelable questionPar = intent.getParcelableExtra("question");
            questions.add(Parcels.unwrap(questionPar));
        }
    }
}
