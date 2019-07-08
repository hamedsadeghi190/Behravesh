package com.partgah.behravesh.Utilities;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;

import com.cedarstudios.cedarmapssdk.CedarMaps;
import com.partgah.behravesh.R;

public class mApplication extends Application {
    public static Context context;
    public static LayoutInflater Inflator;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        Inflator = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FontHelper.Fontiran = Typeface.createFromAsset(context.getAssets(), "fonts/iran.ttf");

        CedarMaps.getInstance()
                .setClientID(getString(R.string.client_id))
                .setClientSecret(getString(R.string.client_secret))
                .setContext(this);
    }
}
