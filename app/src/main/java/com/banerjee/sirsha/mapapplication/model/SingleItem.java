package com.banerjee.sirsha.mapapplication.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Sirsha Banerjee on 16/2/18.
 */

/**
 * Class is responsible for getting and setting data for one single location
 */
public class SingleItem implements ClusterItem {
    private final LatLng mPosition;
    private String mTitle;
    private String mSnippet;


    public SingleItem(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }

    /**
     * Set the title of the marker
     *
     * @param title string to be set as title
     */
    public void setTitle(String title) {
        mTitle = title;
    }

}
