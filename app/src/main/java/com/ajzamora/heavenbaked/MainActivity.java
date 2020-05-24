package com.ajzamora.heavenbaked;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

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
        launchToast(clickedItemIndex);
    }

    private void launchToast(int clickedItemIndex) {
        String currentRecipeName = mAdapter.getItem(clickedItemIndex);
        Toast.makeText(this, " " + currentRecipeName, Toast.LENGTH_SHORT).show();
    }
}
