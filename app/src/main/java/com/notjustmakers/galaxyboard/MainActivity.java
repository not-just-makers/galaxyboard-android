package com.notjustmakers.galaxyboard;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.notjustmakers.galaxyboard.api.DemoGalaxyBoardApi;
import com.notjustmakers.galaxyboard.api.GalaxyBoardApi;
import com.notjustmakers.galaxyboard.model.Problem;
import com.notjustmakers.galaxyboard.ui.common.OnFragmentInteractionListener;
import com.notjustmakers.galaxyboard.ui.problems.AddProblemFragment;
import com.notjustmakers.galaxyboard.ui.problems.DisplayProblemFragment;
import com.notjustmakers.galaxyboard.ui.problems.OnProblemInteractionListener;
import com.notjustmakers.galaxyboard.ui.problems.ProblemListFragment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
    OnFragmentInteractionListener,
    OnProblemInteractionListener {

    private GalaxyBoardApi galaxyBoardApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Add on-click to floating action button
        findViewById(R.id.floatingActionButton).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeFragment(AddProblemFragment.newInstance(galaxyBoardApi));
                }
            }
        );

        /*galaxyBoardApi = new Retrofit.Builder()
            .baseUrl("http://192.168.0.196")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GalaxyBoardApi.class);*/

        galaxyBoardApi = new DemoGalaxyBoardApi();

        changeFragment(ProblemListFragment.newInstance(galaxyBoardApi));
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

        Fragment fragment = null;

        if (id == R.id.nav_problems) {
            fragment = ProblemListFragment.newInstance(galaxyBoardApi);
        } else if (id == R.id.nav_connection) {

        } else if (id == R.id.nav_board_settings) {

        }

        changeFragment(fragment);

        return true;
    }

    private void changeFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onTitleChange(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onFloatingActionButtonChange(boolean visible) {
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        if (visible) { floatingActionButton.show(); }
        else { floatingActionButton.hide(); }
    }

    @Override
    public void onProblemChange(Problem problem) {
        changeFragment(DisplayProblemFragment.newInstance(problem));
    }
}
