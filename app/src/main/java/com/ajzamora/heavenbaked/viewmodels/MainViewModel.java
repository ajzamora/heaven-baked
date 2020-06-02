package com.ajzamora.heavenbaked.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.utils.AppDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static final String LOG_TAG = MainViewModel.class.getSimpleName();

    private LiveData<List<Recipe>> recipeList;

    public MainViewModel(Application application) {
        super(application);
        AppDatabase database = AppDatabase.getInstance(this.getApplication());
        Log.d(LOG_TAG, "Actively retrieving the recipes from the DataBase");
        recipeList = database.recipeDao().getRecipeList();
    }

    public LiveData<List<Recipe>> getRecipeList() {
        return recipeList;
    }
}