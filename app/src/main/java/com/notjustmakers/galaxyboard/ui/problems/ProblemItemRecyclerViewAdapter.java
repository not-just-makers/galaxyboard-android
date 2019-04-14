package com.notjustmakers.galaxyboard.ui.problems;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notjustmakers.galaxyboard.R;
import com.notjustmakers.galaxyboard.model.Problem;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProblemItemRecyclerViewAdapter extends RecyclerView.Adapter<ProblemItemRecyclerViewAdapter.ViewHolder> {

    private final List<Problem> mValues;
    private final OnProblemInteractionListener onProblemInteractionListener;

    public ProblemItemRecyclerViewAdapter(OnProblemInteractionListener listener) {
        mValues = new ArrayList<>();
        onProblemInteractionListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.problem_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getName());
        holder.mContentView.setText(mValues.get(position).getDifficulty());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onProblemInteractionListener) {
                    onProblemInteractionListener.onProblemChange(holder.mItem);
                }
            }
        });
    }

    public List<Problem> getProblems() {
        return mValues;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Problem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
