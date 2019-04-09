package com.notjustmakers.galaxyboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import petrov.kristiyan.colorpicker.ColorPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.notjustmakers.galaxyboard.api.GalaxyBoardApi;
import com.notjustmakers.galaxyboard.model.Color;
import com.notjustmakers.galaxyboard.model.Status;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddProblemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddProblemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddProblemFragment extends Fragment {

    private static final Integer N_ROWS = 10;
    private static final Integer N_COLUMNS = 5;

    private ArrayList<String> ledColors;
    private GalaxyBoardApi galaxyBoardApi;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddProblemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddProblemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddProblemFragment newInstance(String param1, String param2) {
        AddProblemFragment fragment = new AddProblemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        initializeApi();
        initializeLedColors();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mListener != null) {
            mListener.onFragmentInteraction("Add Problem");
        }
        View view = inflater.inflate(R.layout.fragment_add_problem, container, false);

        createButtonMatrix(view);

        return view;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String title);
    }

    private void createButtonMatrix(View view) {
        TableLayout tableLayout = (TableLayout) view.findViewById(R.id.ledMatrix);
        tableLayout.setWeightSum(N_ROWS);

        for (int i=0; i<N_ROWS; i++) {
            TableRow tableRow = new TableRow(getContext());
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1));
            tableRow.setWeightSum(N_COLUMNS);
            tableLayout.addView(tableRow);
            for (int j = 0; j < N_COLUMNS; j++) {
                final int ledPosition = j % 2 == 0 ? ((N_ROWS - i - 1) + j * N_ROWS) :  (i + j * N_ROWS);
                final ImageView button = new ImageView(getContext());
                button.setImageResource(getRandomWallGrip());
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openColorPicker(ledPosition, button);
                    }
                });
                tableRow.addView(button);
            }
        }
    }

    private int getRandomWallGrip() {
        int i = new Random().nextInt(5);
        switch (i) {
            case 0:
                return R.drawable.ic_wallgrip1;
            case 1:
                return R.drawable.ic_wallgrip2;
            case 2:
                return R.drawable.ic_wallgrip3;
            case 3:
                return R.drawable.ic_wallgrip4;
            default:
                return R.drawable.ic_wallgrip5;
        }
    }

    private void initializeLedColors() {
        ledColors = new ArrayList<>();
        ledColors.add("#ffffff"); // WHITE
        ledColors.add("#c0c0c0"); // LIGHT GRAY
        ledColors.add("#808080"); // GRAY
        ledColors.add("#404040"); // DARK_GRAY
        ledColors.add("#000000"); // BLACK
        ledColors.add("#ff0000"); // RED
        ledColors.add("#ffafaf"); // PINK
        ledColors.add("#ffc800"); // ORANGE
        ledColors.add("#ffff00"); // YELLOW
        ledColors.add("#00ff00"); // GREEN
        ledColors.add("#ff00ff"); // MAGENTA
        ledColors.add("#00ffff"); // CYAN
        ledColors.add("#0000ff"); // BLUE
    }

    private void initializeApi() {
        galaxyBoardApi = new Retrofit.Builder()
            .baseUrl("http://192.168.0.196")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GalaxyBoardApi.class);
    }

    private void openColorPicker(final int ledPosition, final ImageView button) {
        new ColorPicker(getActivity())
            .setColors(ledColors)
            .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                @Override
                public void onChooseColor(int position, final int color) {
                    galaxyBoardApi.setPixel(new Color(color), ledPosition).enqueue(new Callback<Status>() {
                        @Override
                        public void onResponse(Call<Status> call, Response<Status> response) {
                            button.setBackgroundColor(color);
                        }

                        @Override
                        public void onFailure(Call<Status> call, Throwable t) {
                            Log.e("t: {}", t.getMessage(), t);
                        }
                    });
                }

                @Override
                public void onCancel(){
                    // put code
                }
            })
            .setRoundColorButton(true)
            .show();
    }
}
