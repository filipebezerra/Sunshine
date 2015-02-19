package io.github.filipebezerra.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @author Filipe Bezerra
 */
public class DetailActivity extends ActionBarActivity {

    public static final String EXTRA_FORECAST = "EXTRA_FORECAST";
    private static final String TAG_DETAIL_FRAGMENT = DetailFragment.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment(), TAG_DETAIL_FRAGMENT)
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
                startActivity(new Intent(DetailActivity.this, SettingsActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static class DetailFragment extends Fragment {

        private TextView mForecastView;

        public DetailFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            mForecastView = (TextView) rootView.findViewById(R.id.forecast_text_view);
            mForecastView.setText(getActivity().getIntent().getStringExtra(EXTRA_FORECAST));
            return rootView;
        }
    }

}
