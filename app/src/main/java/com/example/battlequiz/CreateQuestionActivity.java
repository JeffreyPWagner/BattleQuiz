package com.example.battlequiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.parceler.Parcels;

public class CreateQuestionActivity extends AppCompatActivity {

    EditText questionTextInput;

    EditText choice1Input;
    EditText choice2Input;
    EditText choice3Input;
    EditText choice4Input;
    int questionIndex = -1;

    int RESULT = CreateQuizActivity.QUESTION_RESULT;

    Button saveQuestionBut;

    RadioGroup answerIdentifier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_question_activity);

        //QuestionIndex Stuff
        Intent editQuestionIntent = getIntent();
        Parcelable quizPar = editQuestionIntent.getParcelableExtra("quiz");
        Question editQuestion = Parcels.unwrap(quizPar);

        questionTextInput = findViewById(R.id.question_text_input);

        choice1Input = findViewById(R.id.choice_1_input);
        choice2Input = findViewById(R.id.choice_2_input);
        choice3Input = findViewById(R.id.choice_3_input);
        choice4Input = findViewById(R.id.choice_4_input);

        saveQuestionBut = findViewById(R.id.save_question_but);

        answerIdentifier = findViewById(R.id.answer_identifier);

        if(editQuestion != null){
            if(editQuestion.getChoices() != null){
                RESULT = CreateQuizActivity.EDIT_QUESTION_RESULT;
                if(editQuestion.getChoices().get(0) != null){
                    choice1Input.setText(editQuestion.getChoices().get(0));
                }
                if(editQuestion.getChoices().get(1) != null){
                    choice2Input.setText(editQuestion.getChoices().get(1));
                }
                if(editQuestion.getChoices().get(2) != null){
                    choice3Input.setText(editQuestion.getChoices().get(2));
                }
                if(editQuestion.getChoices().get(3) != null){
                    choice4Input.setText(editQuestion.getChoices().get(3));
                }
                if(editQuestion.getQuestionText() != null){
                    questionTextInput.setText(editQuestion.getQuestionText());
                }
                if(editQuestion.getAnswerIndex() != -1){
                    int i = editQuestion.getAnswerIndex();
                    RadioButton r = (RadioButton) answerIdentifier.getChildAt(i);
                    r.setChecked(true);
                }
                questionIndex = editQuestion.getQuestionIndex();
            }
        }


        // Takes information from inputs and sends it back as a new question when the save button is pressed
        saveQuestionBut.setOnClickListener((View v) -> {
            Question question = new Question(questionTextInput.getText().toString(), choice1Input.getText().toString(), choice2Input.getText().toString(), choice3Input.getText().toString(),
                    choice4Input.getText().toString(), answerIdentifier.indexOfChild(findViewById(answerIdentifier.getCheckedRadioButtonId())),questionIndex);
            Intent questionIntent = new Intent();
            Parcelable questionParcel = Parcels.wrap(question);
            questionIntent.putExtra("question", questionParcel);
            setResult(RESULT, questionIntent);
            finish();
        });
    }
}
