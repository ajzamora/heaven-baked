package com.ajzamora.heavenbaked;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ajzamora.heavenbaked.databinding.ActivityMainBinding;
import com.ajzamora.heavenbaked.utils.LayoutUtils;

public class MainActivity extends AppCompatActivity {
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
        mAdapter = new RecipeAdapter();
        mMainBinding.rvRecipeMain.setAdapter(mAdapter);
        mMainBinding.rvRecipeMain.setLayoutManager(new GridLayoutManager(this, COLUMN_NUM));
        mMainBinding.rvRecipeMain.setHasFixedSize(true);
    }

}
