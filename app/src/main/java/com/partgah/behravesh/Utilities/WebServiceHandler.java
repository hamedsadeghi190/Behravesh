package com.partgah.behravesh.Utilities;

import com.partgah.behravesh.WebService.Icategory;

import java.util.concurrent.TimeUnit;

import retrofit.GsonConverterFactory;

public class WebServiceHandler {

    private static com.squareup.okhttp.OkHttpClient okHttpClient = new com.squareup.okhttp.OkHttpClient();

    private static com.squareup.okhttp.OkHttpClient getOkHttpClient() {
        okHttpClient.setReadTimeout(1, TimeUnit.MINUTES);
        okHttpClient.setConnectTimeout(1, TimeUnit.MINUTES);
        return okHttpClient;
    }
    private static retrofit.Retrofit retrofit = new retrofit.Retrofit.Builder()
            .baseUrl(SharedData.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build();

    public static Icategory CategoryClinet = retrofit.create(Icategory.class);
}
