package com.partgah.behravesh.Models;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.cedarstudios.cedarmapssdk.CedarMaps;
import com.cedarstudios.cedarmapssdk.listeners.ReverseGeocodeResultListener;
import com.cedarstudios.cedarmapssdk.model.geocoder.reverse.ReverseGeocode;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.partgah.behravesh.Models.Listners.locationLisitner;
import com.partgah.behravesh.R;

public class LocationRepository {

    private LocationManager locationManager;
    Context _context;

    @SuppressLint("MissingPermission")
    public void findGpsLocation(Context context, locationLisitner listner) {
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                listner.OnFound(new locationModel(location));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 1, listener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, listener);
    }


    public void FindGeoAddress(Context context,LatLng point,locationLisitner lisitner)
    {
        _context = context;
        CedarMaps.getInstance().reverseGeocode(point,
                new ReverseGeocodeResultListener() {
                    @Override
                    public void onSuccess(@NonNull ReverseGeocode result) {
                        Location location = new Location("dummyprovider");
                        location.setLatitude(point.getLatitude());
                        location.setLatitude(point.getLatitude());
                        locationModel locationModel = new locationModel(location,fullAddressForItem(result));
                        lisitner.OnFound(locationModel);
                    }

                    @Override
                    public void onFailure(@NonNull String errorMessage) {
                        lisitner.OnError(errorMessage);
                    }
                });
    }

    private String fullAddressForItem(ReverseGeocode item) {
        String result = "";

        if (!TextUtils.isEmpty(item.getCity())) {
            if (TextUtils.isEmpty(result)) {
                result = item.getCity();
            } else {
                result = result + _context.getString(R.string.comma) + " " + item.getCity();
            }
        }

        if (!TextUtils.isEmpty(item.getLocality())) {
            if (TextUtils.isEmpty(result)) {
                result = item.getLocality();
            } else {
                result = result + _context.getString(R.string.comma) + " " + item.getLocality();
            }
        }

        if (!TextUtils.isEmpty(item.getAddress())) {
            if (TextUtils.isEmpty(result)) {
                result = item.getAddress();
            } else {
                result = result + _context.getString(R.string.comma) + " " + item.getAddress();
            }
        }

        if (!TextUtils.isEmpty(item.getPlace())) {
            if (TextUtils.isEmpty(result)) {
                result = item.getPlace();
            } else {
                result = result + _context.getString(R.string.comma) + " " + item.getPlace();
            }
        }

        return result;
    }
}
