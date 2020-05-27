package com.ajzamora.heavenbaked.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ajzamora.heavenbaked.R;
import com.ajzamora.heavenbaked.adapters.RecipeAdapter;
import com.ajzamora.heavenbaked.utils.AppDatabase;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.databinding.ActivityMainBinding;
import com.ajzamora.heavenbaked.interfaces.IRecyclerItemClickListener;
import com.ajzamora.heavenbaked.utils.LayoutUtils;

public class MainActivity extends AppCompatActivity implements IRecyclerItemClickListener {
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
//        mDb.recipeDao().insertRecipe(new Recipe(1L, "first", 1, new Step()));
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
        String stepStr = recipe.step == null ? "Sample Recipe Step" : recipe.step.getStepsJsonArray();
        recipeDetail.putExtra(DetailActivity.EXTRA_RECIPE, stepStr);
        startActivity(recipeDetail);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.setRecipes(mDb.recipeDao().loadAllTasks());
    }
}
