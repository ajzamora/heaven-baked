package com.ajzamora.heavenbaked.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ajzamora.heavenbaked.R;
import com.ajzamora.heavenbaked.adapters.RecipeAdapter;
import com.ajzamora.heavenbaked.databinding.ActivityMainBinding;
import com.ajzamora.heavenbaked.utils.LayoutUtils;

public class MainActivity extends AppCompatActivity implements RecipeAdapter.RecyclerItemClickListener {
    ActivityMainBinding mMainBinding;
    RecipeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());
        initUI();
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
        String recipe = mAdapter.getItem(clickedItemIndex);
        Intent recipeDetail = new Intent(this, DetailActivity.class);
        recipeDetail.putExtra(DetailActivity.EXTRA_RECIPE, recipe);
        startActivity(recipeDetail);
    }
}
