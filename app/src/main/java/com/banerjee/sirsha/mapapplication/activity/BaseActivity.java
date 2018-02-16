package com.banerjee.sirsha.mapapplication.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.banerjee.sirsha.mapapplication.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by Sirsha Banerjee on 16/2/18.
 */

/**
 * Base Activity class performs common functions and provides common features to the child activities.
 */
public abstract class BaseActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    /**
     * Returns the resource id of the layout which will be used for setContentView() for the Activity
     *
     * @return resource id of the xml layout
     */
    protected int getLayoutId() {
        return R.layout.activity_map;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        setUpMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMap();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        if (mMap != null) {
            return;
        }
        mMap = map;
        startFunctionalities();
    }

    private void setUpMap() {
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
    }

    /**
     * Implement the required functionalities
     */
    protected abstract void startFunctionalities();

    /**
     * Returns the google map and creates a visible map on the screen
     *
     * @return google map
     */
    protected GoogleMap getMap() {
        return mMap;
    }
}
