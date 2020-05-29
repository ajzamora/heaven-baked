package com.ajzamora.heavenbaked.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajzamora.heavenbaked.adapters.RecipeDetailsExpandableListAdapter;
import com.ajzamora.heavenbaked.data.Ingredient;
import com.ajzamora.heavenbaked.data.Step;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.databinding.FragRecipeDetailBinding;
import com.ajzamora.heavenbaked.ui.DetailActivity;
import com.ajzamora.heavenbaked.ui.StepActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecipeDetailFragment extends Fragment {
    private static final int INGREDIENT_GROUP_POSITION = 0;
    private static final int STEP_GROUP_POSITION = 1;
    private static final String SUFFIX_S = "s";
    private Recipe mRecipe;
    private FragRecipeDetailBinding mFragRecipeDetailBinding;

    ExpandableListAdapter mExpandableListAdapter;
    List<String> mExpandableListTitle;
    HashMap<String, List<String>> mExpandableListDetail;


    public RecipeDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initializeExpandableListData();
        initUI(container);
        return mFragRecipeDetailBinding.getRoot();
    }

    private void initializeExpandableListData() {
        mExpandableListTitle = new ArrayList<>();
        mExpandableListDetail = new HashMap<>();
        mRecipe = getActivity().getIntent().getParcelableExtra(DetailActivity.EXTRA_RECIPE);

        test(Ingredient.class.getSimpleName().concat(SUFFIX_S).toUpperCase(), mRecipe.getIngredients());
        test(Step.class.getSimpleName().concat(SUFFIX_S).toUpperCase(), mRecipe.getSteps());
    }

    private <T> void test(final String title, List<T> objList) {
        mExpandableListTitle.add(title);
        List<String> values = new ArrayList<>();
        for (T obj : objList) {
            if (obj instanceof Ingredient) values.add(((Ingredient) obj).getIngredient());
            else if (obj instanceof Step) values.add(((Step) obj).getShortDescription());
        }
        mExpandableListDetail.put(title, values);
    }

    private void initUI(ViewGroup container) {
        mFragRecipeDetailBinding = FragRecipeDetailBinding.inflate(LayoutInflater.from(getContext()), container, false);

        mExpandableListAdapter = new RecipeDetailsExpandableListAdapter(getContext(), mExpandableListTitle, mExpandableListDetail);
        mFragRecipeDetailBinding.expandableListView.setAdapter(mExpandableListAdapter);
        mFragRecipeDetailBinding.expandableListView.expandGroup(INGREDIENT_GROUP_POSITION);
        mFragRecipeDetailBinding.expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        mExpandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mFragRecipeDetailBinding.expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity().getApplicationContext(),
                        mExpandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        mFragRecipeDetailBinding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity().getApplicationContext(),
                        mExpandableListTitle.get(groupPosition)
                                + " -> "
                                + mExpandableListDetail.get(
                                mExpandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                if (groupPosition == STEP_GROUP_POSITION) launchRecipeStep(childPosition);
                return false;
            }
        });
    }

    private void launchRecipeStep(int clickedItemIndex) {
        Intent recipeStep = new Intent(getContext(), StepActivity.class);
        recipeStep.putExtra(RecipeStepFragment.EXTRA_STEP, mRecipe.getSteps().get(clickedItemIndex).getShortDescription());
        startActivity(recipeStep);
    }
}