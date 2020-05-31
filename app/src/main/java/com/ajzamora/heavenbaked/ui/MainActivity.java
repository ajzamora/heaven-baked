package com.ajzamora.heavenbaked.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ajzamora.heavenbaked.R;
import com.ajzamora.heavenbaked.adapters.RecipeAdapter;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.databinding.ActivityMainBinding;
import com.ajzamora.heavenbaked.interfaces.IRecyclerItemClickListener;
import com.ajzamora.heavenbaked.utils.AppDatabase;
import com.ajzamora.heavenbaked.utils.AppExecutors;
import com.ajzamora.heavenbaked.utils.LayoutUtils;
import com.ajzamora.heavenbaked.utils.NetworkUtils;
import com.ajzamora.heavenbaked.utils.RecipeUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IRecyclerItemClickListener {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private AppDatabase mDb;
    private ActivityMainBinding mMainBinding;
    private RecipeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());
        initUI();
        mDb = AppDatabase.getInstance(getApplicationContext());
        fetchDbData();

        // TODO: use repository;
        // not working : fetching with live data is delayed
        if (mAdapter.getItemCount()<=0 && NetworkUtils.isOnline(this)) { fetchNetworkData(); }
    }

    private void initUI() {
        final int COLUMN_WIDTH = getResources().getInteger(R.integer.rv_recipe_column_widthDp);
        final int COLUMN_NUM = LayoutUtils.calculateNoOfColumns(this, COLUMN_WIDTH);
        mAdapter = new RecipeAdapter(this);
        mMainBinding.rvRecipeMain.setAdapter(mAdapter);
        mMainBinding.rvRecipeMain.setLayoutManager(new GridLayoutManager(this, COLUMN_NUM));
        mMainBinding.rvRecipeMain.setHasFixedSize(true);
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        launchRecipeDetail(clickedItemIndex);
    }

    private void launchRecipeDetail(int clickedItemIndex) {
        Recipe recipe = mAdapter.getItem(clickedItemIndex);
        Intent recipeDetail = new Intent(this, DetailActivity.class);
        recipeDetail.putExtra(DetailActivity.EXTRA_RECIPE, (Parcelable) recipe);
        startActivity(recipeDetail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchDbData();
    }

    private void fetchDbData() {
        final LiveData<List<Recipe>> recipeList = mDb.recipeDao().getRecipeList();
        recipeList.observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                mAdapter.setRecipes(recipes);
            }
        });
    }

    private void fetchNetworkData() {
        AppExecutors.getInstance().networkIO().execute(new Runnable() {
            @Override
            public void run() {
                URL recipeRequestUrl = NetworkUtils.buildUrl(RecipeUtils.BASE_URL, RecipeUtils.TOPHER_2017_SAMPLE_BAKING_PATH);
                Log.v(LOG_TAG, recipeRequestUrl.toString());
                List<Recipe> simpleJsonRecipeData = null;
                String jsonRecipeResponse = null;
                try {
                    jsonRecipeResponse = NetworkUtils
                            .getResponseFromHttpUrl(recipeRequestUrl);
                    simpleJsonRecipeData = RecipeUtils
                            .parseRecipe((new JSONArray(jsonRecipeResponse)));
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Problem retrieving the recipe JSON results.", e);
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "Problem parsing the recipe JSON results: " + jsonRecipeResponse, e);
                }
                final List<Recipe> finalSimpleJsonRecipeData = simpleJsonRecipeData;
                
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() { mDb.recipeDao().insertAllRecipe(finalSimpleJsonRecipeData); }
                });
            }
        });
    }
}
