package com.partgah.behravesh.Views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cedarstudios.cedarmapssdk.CedarMapsStyle;
import com.cedarstudios.cedarmapssdk.CedarMapsStyleConfigurator;
import com.cedarstudios.cedarmapssdk.MapView;
import com.cedarstudios.cedarmapssdk.listeners.OnStyleConfigurationListener;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Style;
import com.partgah.behravesh.Models.locationModel;
import com.partgah.behravesh.R;
import com.partgah.behravesh.Utilities.FontHelper;
import com.partgah.behravesh.Utilities.SharedData;
import com.partgah.behravesh.ViewModels.mainViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    MapboxMap mMapboxMap;
    Context context;
    ArrayList<Marker> markers = new ArrayList<Marker>();

    @BindView(R.id.LayoutDrawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.mapView)
    MapView mMapView;

    @BindView(R.id.txt_address)
    TextView txtAddress;

    @BindView(R.id.lyt_bottom_menu)
    LinearLayout lyt_bottom_menu;

    @BindView(R.id.step_view)
    com.shuhart.stepview.StepView step_view;

    @BindView(R.id.btn_add_home_recycle)
    TextView btn_add_home_recycle;

    @BindView(R.id.btn_add_sanati_recycle)
    TextView btn_add_sanati_recycle;


    mainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = this;
        initViewModel();
        initControls();
        loadMap();

    }

    private void initControls() {
        txtAddress.setTypeface(FontHelper.Fontiran);
        btn_add_home_recycle.setTypeface(FontHelper.Fontiran);
        btn_add_sanati_recycle.setTypeface(FontHelper.Fontiran);
    }

    private void ClickHandler() {

        mMapboxMap.addOnMapClickListener(point -> {
            for (Marker marker : markers) {
                mMapboxMap.removeMarker(marker);
            }
            addMarkerToMapViewAtPosition(point);
            mViewModel.FindGeoAddress(this, point);
            return false;
        });

    }

    private void loadMap() {
        mMapView.getMapAsync(mapboxMap -> {
            mMapboxMap = mapboxMap;
            CedarMapsStyleConfigurator.configure(
                    CedarMapsStyle.VECTOR_LIGHT, new OnStyleConfigurationListener() {
                        @Override
                        public void onSuccess(Style.Builder styleBuilder) {
                            mapboxMap.setStyle(styleBuilder);
                        }

                        @Override
                        public void onFailure(@NonNull String errorMessage) {
                            Log.e("tag", errorMessage);
                        }
                    });

            mMapboxMap.setMaxZoomPreference(17);
            mMapboxMap.setMinZoomPreference(6);

            mMapboxMap.setCameraPosition(new CameraPosition.Builder()
                    .target(SharedData.location)
                    .zoom(15)
                    .build());
            addMarkerToMapViewAtPosition(SharedData.location);
            mViewModel.FindGeoAddress(this, SharedData.location);
            ClickHandler();
        });
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this).get(mainViewModel.class);
        final Observer<locationModel> observer = locationModel -> {
            txtAddress.setText(locationModel.getAddress());
        };
        mViewModel.locationModelLiveData.observe(this, observer);

    }

    @SuppressLint("WrongConstant")
    @OnClick(R.id.Nav)
    public void NavIcon_Clicked() {
        drawerLayout.openDrawer(Gravity.END);
    }

    @OnClick(R.id.btn_add_home_recycle)
    public void home_recycle_Clicked() {
        Intent intent = new Intent(this,CategoryActivity.class);
        startActivity(intent);

    }

    private void addMarkerToMapViewAtPosition(LatLng coordinate) {
        if (mMapboxMap != null && context != null) {
            Marker marker = mMapboxMap.addMarker(new MarkerOptions()
                    .position(coordinate)
                    .icon(IconFactory.getInstance(context).fromResource(R.drawable.ic_origin))
            );
            mMapboxMap.setOnMarkerClickListener(marker1 -> {
                marker1.setIcon(IconFactory.getInstance(context).fromResource(R.drawable.ic_origin_selected));
                lyt_bottom_menu.setVisibility(View.VISIBLE);
                return false;
            });
            lyt_bottom_menu.setVisibility(View.GONE);
            markers.add(marker);

        }
    }
}
