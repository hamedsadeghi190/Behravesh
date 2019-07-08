package com.partgah.behravesh.WebService;

import retrofit.Call;
import retrofit.http.GET;

public interface Icategory {
    @GET("v2/5d0c98a33500005900b89964")
    Call<refuseTypes> GetCategories();

}
