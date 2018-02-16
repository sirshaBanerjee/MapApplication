package com.banerjee.sirsha.mapapplication.activity;

import android.widget.Toast;

import com.banerjee.sirsha.mapapplication.R;
import com.banerjee.sirsha.mapapplication.model.SingleItem;
import com.banerjee.sirsha.mapapplication.util.ItemReader;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Sirsha Banerjee on 16/2/18.
 */

/**
 * MapActivity class displays a map on the screen with markers pointed in different locations, when zoomed out markers sum up to one and displays
 * the total number of markers present and when zoomed in, all markers are separated and points exact location
 */
public class MapActivity extends BaseActivity {
    private ClusterManager<SingleItem> mClusterManager;

    @Override
    protected void startFunctionalities() {
        getMap().moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(51.503186, -0.126446), 10));

        mClusterManager = new ClusterManager<>(this, getMap());
        getMap().setOnCameraIdleListener(mClusterManager);

        try {
            readItems();
        } catch (JSONException e) {
            Toast.makeText(this, getString(R.string.alert_json_failed), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * method reads latitude and longitude of different location from a raw json file and puts markers on those locations
     *
     * @throws JSONException
     */
    private void readItems() throws JSONException {
        final InputStream inputStream = getResources().openRawResource(R.raw.radar_search);
        final List<SingleItem> items = new ItemReader(this).read(inputStream);
        mClusterManager.addItems(items);
    }
}
