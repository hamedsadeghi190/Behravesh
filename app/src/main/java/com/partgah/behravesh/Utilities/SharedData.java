package com.partgah.behravesh.Utilities;

import com.mapbox.mapboxsdk.geometry.LatLng;

public class SharedData {

    private static final SharedData ourInstance = new SharedData();
    public static LatLng location = new LatLng();
    public final static String BaseUrl = "http://www.mocky.io/";

    public static SharedData getInstance() {
        return ourInstance;
    }

    private SharedData() {
    }
}
