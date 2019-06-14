package com.example.battlequiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class LeaderAdapter extends RecyclerView.Adapter<LeaderAdapter.LeaderViewHolder> {

    private List<String> highScores;

    public LeaderAdapter(List<String> highScores) {
        this.highScores = highScores;
    }

    @NonNull
    @Override
    public LeaderViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View leaderView = inflater.inflate(R.layout.leaderboard_list_item, viewGroup, false);
        return new LeaderViewHolder(leaderView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderViewHolder viewHolder, int i) {
        if (highScores != null && highScores.size() != 0) {
            viewHolder.leader = highScores.get(i);
            viewHolder.leaderText.setText(highScores.get(i));
        }
    }

    @Override
    public int getItemCount() {
        if (highScores == null) {
            return 0;
        }
        else {
            return highScores.size();
        }
    }

    public class LeaderViewHolder extends RecyclerView.ViewHolder {

        public TextView leaderText;
        public String leader;

        public LeaderViewHolder(View itemView) {
            super(itemView);
            leaderText = itemView.findViewById(R.id.leader_text);
        }
    }
}
