package com.partgah.behravesh.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.partgah.behravesh.Models.CategoryModel;
import com.partgah.behravesh.Models.Listners.WebServicelistener;
import com.partgah.behravesh.WebService.CategoryRepostiroy;

import java.util.ArrayList;

public class CategoryViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<CategoryModel>> CategoryModelLiveData = new MutableLiveData<>();

    public CategoryViewModel(@NonNull Application application) {
        super(application);
    }

    public void GetCategories() {
        WebServicelistener servicelistener = new WebServicelistener() {
            @Override
            public void OnComplete(Object object) {
                ArrayList<CategoryModel> categoryModels = (ArrayList<CategoryModel>) object;
                CategoryModelLiveData.postValue(null);
                CategoryModelLiveData.postValue(categoryModels);
            }

            @Override
            public void OnFail(Object object) {

            }
        };
        new CategoryRepostiroy().GetCategories(servicelistener);
    }
}
