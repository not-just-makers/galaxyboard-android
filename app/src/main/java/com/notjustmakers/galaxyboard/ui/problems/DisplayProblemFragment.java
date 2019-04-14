package com.notjustmakers.galaxyboard.ui.problems;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.notjustmakers.galaxyboard.R;
import com.notjustmakers.galaxyboard.model.ClimbingWallMatrix;
import com.notjustmakers.galaxyboard.model.Problem;
import com.notjustmakers.galaxyboard.ui.common.OnFragmentInteractionListener;

import androidx.fragment.app.Fragment;

/**
 * This fragment represents the screen to display an existent problem to GalaxyBoard.
 *
 * @author Andres Sanchez (andressanchez)
 */
public class DisplayProblemFragment extends Fragment {

    private OnFragmentInteractionListener onFragmentInteractionListener;
    private OnProblemInteractionListener onProblemInteractionListener;

    private ClimbingWallMatrix climbingWallMatrix;
    private Problem problem;

    public DisplayProblemFragment() {
        // Required empty public constructor
    }

    public static DisplayProblemFragment newInstance(Problem problem) {
        DisplayProblemFragment displayProblemFragment = new DisplayProblemFragment();
        displayProblemFragment.problem = problem;
        return displayProblemFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_display_problem, container, false);

        // Update title
        if (onFragmentInteractionListener != null) {
            onFragmentInteractionListener.onTitleChange("Problem " + problem.getId());
            onFragmentInteractionListener.onFloatingActionButtonChange(false);
        }

        // Create climbing wall matrix
        climbingWallMatrix = new ClimbingWallMatrix(problem.getRows(), problem.getColumns(), problem.getClimbingHolds());

        // Create climbing wall matrix view
        FrameLayout displayProblemFrame = view.findViewById(R.id.displayProblemFrame);
        ClimbingWallMatrixView climbingWallMatrixView = new ClimbingWallMatrixView(getContext(), getActivity(), climbingWallMatrix, false);
        displayProblemFrame.addView(climbingWallMatrixView);

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
