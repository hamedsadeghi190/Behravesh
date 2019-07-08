package com.partgah.behravesh.ViewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.partgah.behravesh.Models.Listners.locationLisitner;
import com.partgah.behravesh.Models.LocationRepository;
import com.partgah.behravesh.Models.locationModel;

public class mainViewModel extends AndroidViewModel {
    public MutableLiveData<locationModel> locationModelLiveData = new MutableLiveData<>();

    public mainViewModel(@NonNull Application application) {
        super(application);
    }

    public void FindGeoAddress(Context context, LatLng Point) {
        locationLisitner listener = new locationLisitner() {
            @Override
            public void OnFound(locationModel location) {
                locationModelLiveData.setValue(location);
            }

            @Override
            public void OnError(String ErrorMessage) {

            }
        };
        new LocationRepository().FindGeoAddress(context, Point, listener);
    }
}
