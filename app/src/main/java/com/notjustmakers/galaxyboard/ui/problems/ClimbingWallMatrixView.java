package com.notjustmakers.galaxyboard.ui.problems;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.notjustmakers.galaxyboard.R;
import com.notjustmakers.galaxyboard.model.ClimbingHold;
import com.notjustmakers.galaxyboard.model.ClimbingWallMatrix;
import com.notjustmakers.galaxyboard.model.Color;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.res.ResourcesCompat;
import petrov.kristiyan.colorpicker.ColorPicker;

public class ClimbingWallMatrixView extends TableLayout {

    private final Context context;
    private final Activity activity;
    private final ClimbingWallMatrix climbingWallMatrix;
    private final boolean editable;

    private ContextThemeWrapper defaultThemeWrapper;
    private ArrayList<String> ledColors;
    private Map<String, ContextThemeWrapper> themeWrappersByColor;

    public ClimbingWallMatrixView(Context context, Activity activity, ClimbingWallMatrix climbingWallMatrix, boolean editable) {
        super(context);
        this.context = context;
        this.activity = activity;
        this.climbingWallMatrix = climbingWallMatrix;
        this.editable = editable;
        init();
    }

    public ClimbingWallMatrixView(Context context, AttributeSet attrs, Activity activity, ClimbingWallMatrix climbingWallMatrix, boolean editable) {
        super(context, attrs);
        this.context = context;
        this.activity = activity;
        this.climbingWallMatrix = climbingWallMatrix;
        this.editable = editable;
        init();
    }

    private void init() {
        // Default Theme
        defaultThemeWrapper = new ContextThemeWrapper(context, R.style.WallGripTheme);

        // Themes
        themeWrappersByColor = new HashMap<>();
        themeWrappersByColor.put(Color.BLACK.getHex(), defaultThemeWrapper);
        themeWrappersByColor.put(Color.RED.getHex(), new ContextThemeWrapper(context, R.style.WallGripTheme_Red));
        themeWrappersByColor.put(Color.ORANGE.getHex(), new ContextThemeWrapper(context, R.style.WallGripTheme_Orange));
        themeWrappersByColor.put(Color.CYAN.getHex(), new ContextThemeWrapper(context, R.style.WallGripTheme_Cyan));
        themeWrappersByColor.put(Color.YELLOW.getHex(), new ContextThemeWrapper(context, R.style.WallGripTheme_Yellow));

        // Led Colors
        ledColors = new ArrayList<>();
        ledColors.add(Color.BLACK.getHex());
        ledColors.add(Color.RED.getHex());
        ledColors.add(Color.ORANGE.getHex());
        ledColors.add(Color.CYAN.getHex());
        ledColors.add(Color.YELLOW.getHex());

        // Adjust weight to evenly distribute height
        setWeightSum(climbingWallMatrix.getRows());

        // Create rows
        for (int i = 0; i < climbingWallMatrix.getRows(); i++) {
            // Create new row
            TableRow tableRow = new TableRow(context);

            // Use all width and height
            tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1));

            // Adjust weight to evenly distribute width
            tableRow.setWeightSum(climbingWallMatrix.getColumns());

            // Create columns
            for (int j = 0; j < climbingWallMatrix.getColumns(); j++) {
                // Get climbing hold from matrix
                final ClimbingHold climbingHold = climbingWallMatrix.getClimbingHolds()[i][j];

                // Create climbing hold image
                final ImageView climbingHoldImg = new ImageView(getContext());
                climbingHoldImg.setImageDrawable(ResourcesCompat.getDrawable(
                    getResources(),
                    getClimbingHoldImageByType(climbingHold.getType()),
                    themeWrappersByColor.get(climbingHold.getColor().getHex()).getTheme())
                );

                // Make it as big as possible
                climbingHoldImg.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1));

                // Add on-click action
                if (editable) {
                    climbingHoldImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            new ColorPicker(activity)
                                .setColors(ledColors)
                                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                                    @Override
                                    public void onChooseColor(int position, final int _color) {
                                        final Color color = new Color(_color);
                                        climbingHoldImg.setImageDrawable(ResourcesCompat.getDrawable(
                                            getResources(),
                                            getClimbingHoldImageByType(climbingHold.getType()),
                                            themeWrappersByColor.get(color.getHex()).getTheme())
                                        );
                                        /*galaxyBoardApi.setPixel(color, ledPosition).enqueue(new Callback<Status>() {
                                            @Override
                                            public void onResponse(Call<Status> call, Response<Status> response) {
                                                // TODO: Confirm color change
                                            }

                                            @Override
                                            public void onFailure(Call<Status> call, Throwable t) {
                                                Log.e("t: {}", t.getMessage(), t);
                                            }
                                        });*/
                                    }

                                    @Override
                                    public void onCancel() {
                                        // put code
                                    }
                                })
                                .setRoundColorButton(true)
                                .show();
                        }
                    });
                }

                // Add climbing hold to row
                tableRow.addView(climbingHoldImg);
            }

            // Add row to table
            addView(tableRow);
        }
    }

    private int getClimbingHoldImageByType(int type) {
        switch (type) {
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
}
