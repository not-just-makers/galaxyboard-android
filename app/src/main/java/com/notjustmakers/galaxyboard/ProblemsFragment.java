package com.notjustmakers.galaxyboard;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.notjustmakers.galaxyboard.listener.OnFragmentInteractionListener;

import androidx.fragment.app.Fragment;


/**
 * List of problems
 */
public class ProblemsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ProblemsFragment() {
        // Required empty public constructor
    }

    public static ProblemsFragment newInstance() {
        return new ProblemsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_problems, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
