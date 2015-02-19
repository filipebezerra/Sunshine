package io.github.filipebezerra.sunshine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.global, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            case R.id.action_view_map:
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(
                        this);
                String location = defaultSharedPreferences.getString(getString(R.string.pref_location_key),
                        getString(R.string.pref_location_default_value));
                Intent viewLocationOnMap = new Intent(Intent.ACTION_VIEW);
                viewLocationOnMap.setData(Uri.parse("geo:0,0?").buildUpon().appendQueryParameter(
                        "q", location).build());

                if (viewLocationOnMap.resolveActivity(getPackageManager()) != null) {
                    startActivity(viewLocationOnMap);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
