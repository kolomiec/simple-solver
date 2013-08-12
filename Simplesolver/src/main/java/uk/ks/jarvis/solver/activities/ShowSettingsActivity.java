package uk.ks.jarvis.solver.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

import uk.ks.jarvis.solver.R;

/**
 * Created by sayenko on 8/12/13.
 */

public class ShowSettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_settings_layout);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
boolean s = sharedPrefs.getBoolean("checkBox", true);
        StringBuilder builder = new StringBuilder();

        builder.append("\n" + sharedPrefs.getBoolean("checkBox", true));
        builder.append("\n" + sharedPrefs.getString("list", "-1"));
//        builder.append("\n" + sharedPrefs.getString("welcome_message", "NULL"));

        TextView settingsTextView = (TextView) findViewById(R.id.settings_text_view);
        settingsTextView.setText(builder.toString());
    }
}
