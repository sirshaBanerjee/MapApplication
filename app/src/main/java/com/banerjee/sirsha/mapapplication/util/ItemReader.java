package com.banerjee.sirsha.mapapplication.util;

import android.content.Context;

import com.banerjee.sirsha.mapapplication.R;
import com.banerjee.sirsha.mapapplication.model.SingleItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Sirsha Banerjee on 16/2/18.
 */

/**
 * Class is responsible for parsing raw json data of latitude and longitude from a file
 */
public class ItemReader {

    private Context context;

    public ItemReader(Context context) {
        this.context = context;
    }

    /*
    * This matches only once in whole input,
    * so Scanner.next returns whole InputStream as a String.
    */
    private static final String REGEX_INPUT_BOUNDARY_BEGINNING = "\\A";

    public List<SingleItem> read(InputStream inputStream) throws JSONException {
        final List<SingleItem> items = new ArrayList<>();
        final String json = new Scanner(inputStream).useDelimiter(REGEX_INPUT_BOUNDARY_BEGINNING).next();
        final JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            String title = null;
            String snippet = null;
            final JSONObject object = array.getJSONObject(i);
            double lat = object.getDouble(context.getString(R.string.key_latitude));
            double lng = object.getDouble(context.getString(R.string.key_longitude));
            if (!object.isNull(context.getString(R.string.key_title))) {
                title = object.getString(context.getString(R.string.key_title));
            }
            if (!object.isNull(context.getString(R.string.key_snippet))) {
                snippet = object.getString(context.getString(R.string.key_snippet));
            }
            items.add(new SingleItem(lat, lng, title, snippet));
        }
        return items;
    }
}
