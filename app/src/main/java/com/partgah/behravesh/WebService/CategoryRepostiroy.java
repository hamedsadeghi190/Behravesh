package com.partgah.behravesh.WebService;

import com.partgah.behravesh.Models.Listners.WebServicelistener;
import com.partgah.behravesh.Utilities.WebServiceHandler;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class CategoryRepostiroy {

    public void GetCategories(final WebServicelistener listener) {
        try {
            final Call<refuseTypes> CallServer = WebServiceHandler.CategoryClinet.GetCategories();
            CallServer.enqueue(new Callback<refuseTypes>() {
                @Override
                public void onResponse(Response<refuseTypes> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        refuseTypes types = response.body();
                        listener.OnComplete(types.refuseTypes);
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        } catch (Exception ignored) {
            int x = 0;
        }
    }
}
