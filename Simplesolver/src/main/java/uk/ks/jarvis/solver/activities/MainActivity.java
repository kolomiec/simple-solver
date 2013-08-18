package uk.ks.jarvis.solver.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import uk.ks.jarvis.solver.CoordinatePlane.SystemInformation;
import uk.ks.jarvis.solver.R;

public class MainActivity extends FragmentActivity implements View.OnTouchListener, View.OnLongClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_preferences_item) {
            startActivity(new Intent(this, PreferenceActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onLongClick(View v) {
        return true;
    }

    private void setSystemInformation() {
        DisplayMetrics displayMetrics = getBaseContext().getResources().getDisplayMetrics();
        SystemInformation.DISPLAY_WIDTH = displayMetrics.widthPixels;
        SystemInformation.DISPLAY_HEIGHT = displayMetrics.heightPixels;
    }
}
