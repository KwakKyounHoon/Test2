package com.obigo.tmusimulator;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.obigo.tmusimulator.fragment.CarDiagnosticsFragment;
import com.obigo.tmusimulator.fragment.CarInfoFragment;
import com.obigo.tmusimulator.fragment.SettingFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;

    private final String CAR_DIAGNOSTICS_TAG = "diagnostics";
    private final String SETTING_TAG = "setting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        setStartActionBar();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new CarInfoFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        setStartActionBar();

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

        if (item.getItemId() == android.R.id.home) {
            int backStackSize = getSupportFragmentManager().getBackStackEntryCount();
            if(backStackSize == 0) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawer(GravityCompat.START);
                } else {
                    drawer.openDrawer(GravityCompat.START);
                }
                return true;
            }else{
                getSupportFragmentManager().popBackStack();
                setStartActionBar();
            }
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.settings) {
            drawer.closeDrawer(GravityCompat.START);
            Fragment f = getSupportFragmentManager().findFragmentByTag(SETTING_TAG);
            Fragment currentF = getSupportFragmentManager().findFragmentById(R.id.container);
            if(f == null){
                f = new SettingFragment();
                changeFragment(f, SETTING_TAG);
                getSupportActionBar().setTitle(R.string.setting);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
                return true;
            }
            if(currentF instanceof SettingFragment){
                return true;
            }
            return true;
        }

        if (id == R.id.car_diagnostics) {
            drawer.closeDrawer(GravityCompat.START);
            getSupportActionBar().setTitle(R.string.diagnostics);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
            Fragment f = getSupportFragmentManager().findFragmentByTag(CAR_DIAGNOSTICS_TAG);
            Fragment currentF = getSupportFragmentManager().findFragmentById(R.id.container);
            if(f == null){
                f = new CarDiagnosticsFragment();
                changeFragment(f, CAR_DIAGNOSTICS_TAG);
                getSupportActionBar().setTitle(R.string.diagnostics);
                getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_back);
                return true;
            }
            if(currentF instanceof CarDiagnosticsFragment){
                return true;
            }
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment f, String tag) {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, f, tag).addToBackStack(tag)
                .commit();
    }

    private void setStartActionBar(){
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
    }
}
