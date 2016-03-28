package com.darkenrule.tesismaestria;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by Fernando on 08/02/2016.
 */
public class Preferencias extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }
}