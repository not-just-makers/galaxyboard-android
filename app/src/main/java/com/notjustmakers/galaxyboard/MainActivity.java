package com.notjustmakers.galaxyboard;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.notjustmakers.galaxyboard.api.GalaxyBoardApi;
import com.notjustmakers.galaxyboard.model.Color;
import com.notjustmakers.galaxyboard.model.Status;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import petrov.kristiyan.colorpicker.ColorPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final Integer N_ROWS = 10;
    private static final Integer N_COLUMNS = 5;

    private ArrayList<String> ledColors;
    private GalaxyBoardApi galaxyBoardApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TableLayout tableLayout = (TableLayout) findViewById(R.id.ledMatrix);

        for (int i=0; i<N_ROWS; i++) {
            TableRow tableRow = new TableRow(this);
            tableLayout.addView(tableRow);
            for (int j = 0; j < N_COLUMNS; j++) {
                final int ledPosition = j % 2 == 0 ?
                    ((N_ROWS - i - 1) + j * N_ROWS) :  (i + j * N_ROWS);
                final Button button = new Button(this);
                button.setText(String.valueOf(ledPosition));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openColorPicker(ledPosition, button);
                    }
                });
                tableRow.addView(button);
            }
        }

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

        galaxyBoardApi = new Retrofit.Builder()
            .baseUrl("http://192.168.0.196")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GalaxyBoardApi.class);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void openColorPicker(final int ledPosition, final Button button) {
        new ColorPicker(this)
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
