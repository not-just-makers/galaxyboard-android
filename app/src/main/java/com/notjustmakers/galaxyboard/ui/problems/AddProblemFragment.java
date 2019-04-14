package com.notjustmakers.galaxyboard.ui.problems;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.notjustmakers.galaxyboard.R;
import com.notjustmakers.galaxyboard.api.GalaxyBoardApi;
import com.notjustmakers.galaxyboard.model.Board;
import com.notjustmakers.galaxyboard.model.ClimbingWallMatrix;
import com.notjustmakers.galaxyboard.ui.common.OnFragmentInteractionListener;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This fragment represents the screen to add a new problem to GalaxyBoard.
 *
 * @author Andres Sanchez (andressanchez)
 */
public class AddProblemFragment extends Fragment {

    private OnFragmentInteractionListener onFragmentInteractionListener;
    private OnProblemInteractionListener onProblemInteractionListener;

    private ClimbingWallMatrix climbingWallMatrix;
    private GalaxyBoardApi galaxyBoardApi;

    public AddProblemFragment() {
        // Required empty public constructor
    }

    public static AddProblemFragment newInstance(GalaxyBoardApi galaxyBoardApi) {
        AddProblemFragment addProblemFragment = new AddProblemFragment();
        addProblemFragment.galaxyBoardApi = galaxyBoardApi;
        return addProblemFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_add_problem, container, false);

        // Update title
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.onTitleChange("Add Problem");
        }

        // Get problem and create view
        galaxyBoardApi.getBoard().enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                // Create climbing wall matrix
                Board board = response.body();
                climbingWallMatrix = new ClimbingWallMatrix(board.getRows(), board.getColumns(), board.getClimbingHolds());

                // Create climbing wall matrix view
                FrameLayout addProblemFrame = view.findViewById(R.id.addProblemFrame);
                ClimbingWallMatrixView climbingWallMatrixView = new ClimbingWallMatrixView(getContext(), getActivity(), climbingWallMatrix, true);
                addProblemFrame.addView(climbingWallMatrixView);
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener || context instanceof OnProblemInteractionListener) {
            if (context instanceof OnFragmentInteractionListener) {
                onFragmentInteractionListener = (OnFragmentInteractionListener) context;
            }
            if (context instanceof OnProblemInteractionListener) {
                onProblemInteractionListener = (OnProblemInteractionListener) context;
            }
        } else  {
            throw new RuntimeException(context.toString()
                + " must implement OnFragmentInteractionListener and OnProblemInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onFragmentInteractionListener = null;
        onProblemInteractionListener = null;
    }
}
