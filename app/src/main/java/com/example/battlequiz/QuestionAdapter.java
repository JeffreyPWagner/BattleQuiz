package com.example.battlequiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {

    private List<Question> mQuestions;

    public QuestionAdapter(List<Question> questions) {
        mQuestions = questions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View questionView = inflater.inflate(R.layout.question_list_item, viewGroup, false);
        return new ViewHolder(questionView);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder viewHolder, int position) {
        if (mQuestions != null && mQuestions.size() != 0) {
            viewHolder.mQuestion = mQuestions.get(position);
            viewHolder.questionText.setText(viewHolder.mQuestion.getQuestionText());
        }
    }

    @Override
    public int getItemCount() {
        if (mQuestions == null) {
            return 0;
        }
        else {
            return mQuestions.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView questionText;
        public Question mQuestion;

        public ViewHolder(View itemView) {
            super(itemView);
            questionText = itemView.findViewById(R.id.question_text);
        }
    }
}
