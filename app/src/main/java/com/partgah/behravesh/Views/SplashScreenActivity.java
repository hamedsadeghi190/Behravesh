package com.partgah.behravesh.Views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.partgah.behravesh.Models.locationModel;
import com.partgah.behravesh.R;
import com.partgah.behravesh.Utilities.AnimationHelper;
import com.partgah.behravesh.Utilities.SharedData;
import com.partgah.behravesh.ViewModels.splashViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashScreenActivity extends AppCompatActivity {

    @BindView(R.id.img_logo)
    ImageView img_logo;
    splashViewModel sViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);
        initAnimation();
        initViewModel();
    }

    private void initAnimation() {
        img_logo.startAnimation(AnimationHelper.bounce_anim);
    }
    private void initViewModel() {
        sViewModel = ViewModelProviders.of(this).get(splashViewModel.class);
        final Observer<locationModel> observer = locationModel -> {
            SharedData.location.setLatitude(locationModel.getLocation().getLatitude());
            SharedData.location.setLongitude(locationModel.getLocation().getLongitude());
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        };

        sViewModel.locationModelLiveData.observe(this, observer);
        sViewModel.findGpsLocation(this);
    }


}
