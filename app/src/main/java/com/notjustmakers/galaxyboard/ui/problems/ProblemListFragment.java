package com.notjustmakers.galaxyboard.ui.problems;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.notjustmakers.galaxyboard.R;
import com.notjustmakers.galaxyboard.api.GalaxyBoardApi;
import com.notjustmakers.galaxyboard.model.Problem;
import com.notjustmakers.galaxyboard.ui.common.OnFragmentInteractionListener;

import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class ProblemListFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private GalaxyBoardApi galaxyBoardApi;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ProblemListFragment() {
    }

    public static ProblemListFragment newInstance(GalaxyBoardApi galaxyBoardApi) {
        ProblemListFragment problemListFragment = new ProblemListFragment();
        problemListFragment.galaxyBoardApi = galaxyBoardApi;
        return problemListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.problem_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            final ProblemItemRecyclerViewAdapter viewAdapter = new ProblemItemRecyclerViewAdapter(mListener);
            recyclerView.setAdapter(viewAdapter);

            // Get problems from API
            galaxyBoardApi.getProblems().enqueue(new Callback<List<Problem>>() {
                @Override
                public void onResponse(Call<List<Problem>> call, Response<List<Problem>> response) {
                    viewAdapter.getProblems().addAll(response.body());
                }

                @Override
                public void onFailure(Call<List<Problem>> call, Throwable t) {

                }
            });
        }

        // Update title
        if (mListener != null) {
            mListener.onTitleChange("Problems");
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
