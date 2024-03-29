package com.partgah.behravesh.Views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.partgah.behravesh.Adapters.AutoFitGridLayoutManager;
import com.partgah.behravesh.Adapters.RecyclerViewAdapter;
import com.partgah.behravesh.Models.CategoryModel;
import com.partgah.behravesh.R;
import com.partgah.behravesh.ViewModels.CategoryViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.category_recycle)
    RecyclerView recyclerView;

    ArrayList<CategoryModel> arrayList;

    CategoryViewModel cViewModel;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        initViewModel();
        initRecycleView();

    }

    private void initViewModel() {
        cViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
    }


    private void initRecycleView() {

        RecyclerViewAdapter.ItemListener listener = item -> {
        };

        final Observer<ArrayList<CategoryModel>> observer = categoryModels -> {

            adapter = new RecyclerViewAdapter(this, categoryModels, listener);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 300);
            recyclerView.setLayoutManager(layoutManager);
        };
        cViewModel.CategoryModelLiveData.observe(this, observer);
        cViewModel.GetCategories();
    }

}
