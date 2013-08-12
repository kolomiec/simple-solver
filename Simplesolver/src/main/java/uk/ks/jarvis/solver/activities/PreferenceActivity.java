package uk.ks.jarvis.solver.activities;

import android.os.Bundle;

import uk.ks.jarvis.solver.R;

/**
 * Created by sayenko on 8/12/13.
 */
public class PreferenceActivity extends android.preference.PreferenceActivity {

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }
}
