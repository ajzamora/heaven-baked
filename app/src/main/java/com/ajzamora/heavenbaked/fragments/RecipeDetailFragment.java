package com.ajzamora.heavenbaked.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.ajzamora.heavenbaked.R;
import com.ajzamora.heavenbaked.adapters.RecipeDetailAdapter;
import com.ajzamora.heavenbaked.databinding.FragRecipeDetailBinding;
import com.ajzamora.heavenbaked.interfaces.IRecyclerItemClickListener;
import com.ajzamora.heavenbaked.ui.DetailActivity;
import com.ajzamora.heavenbaked.utils.LayoutUtils;

public class RecipeDetailFragment extends Fragment implements IRecyclerItemClickListener {
    private String mRecipe;
    public RecipeDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRecipe = getActivity().getIntent().getStringExtra(DetailActivity.EXTRA_RECIPE);

        final int COLUMN_WIDTH = getResources().getInteger(R.integer.rv_recipe_column_widthDp);
        final int COLUMN_NUM = LayoutUtils.calculateNoOfColumns(getContext(), COLUMN_WIDTH);
        FragRecipeDetailBinding fragRecipeDetailBinding = FragRecipeDetailBinding.inflate(LayoutInflater.from(getContext()), container, false);
        RecipeDetailAdapter recipeDetailAdapter = new RecipeDetailAdapter(mRecipe, this);
        fragRecipeDetailBinding.rvRecipeDetail.setAdapter(recipeDetailAdapter);
        fragRecipeDetailBinding.rvRecipeDetail.setLayoutManager(new GridLayoutManager(getContext(), COLUMN_NUM));
        fragRecipeDetailBinding.rvRecipeDetail.setHasFixedSize(true);
        return fragRecipeDetailBinding.getRoot();
    }

    private void launchRecipeDetail(int clickedItemIndex) {
        Toast.makeText(getContext(), "click :".concat(String.valueOf(clickedItemIndex)), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        launchRecipeDetail(clickedItemIndex);
    }
}
