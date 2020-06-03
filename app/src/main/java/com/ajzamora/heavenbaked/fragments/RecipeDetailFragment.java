package com.ajzamora.heavenbaked.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajzamora.heavenbaked.adapters.RecipeDetailsExpandableListAdapter;
import com.ajzamora.heavenbaked.data.Ingredient;
import com.ajzamora.heavenbaked.data.Step;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.databinding.FragRecipeDetailBinding;
import com.ajzamora.heavenbaked.ui.DetailActivity;
import com.ajzamora.heavenbaked.utils.FormatUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class RecipeDetailFragment extends Fragment {
    private static final int INGREDIENT_GROUP_POSITION = 0;
    private static final int STEP_GROUP_POSITION = 1;
    private static final String SUFFIX_S = "s";
    private Recipe mRecipe;
    private FragRecipeDetailBinding mFragRecipeDetailBinding;

    private ExpandableListAdapter mExpandableListAdapter;
    private List<String> mExpandableListTitle;
    private HashMap<String, List<String>> mExpandableListDetail;


    OnStepClickListener mCallback;

    public interface OnStepClickListener {
        void onStepSelected(int position);
    }

    // Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the host activity has implemented the callback interface
        // If not, it throws an exception
        try {
            mCallback = (OnStepClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnStepClickListener");
        }
    }


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

        mRecipe = Objects.requireNonNull(getActivity()).getIntent().getParcelableExtra(DetailActivity.EXTRA_RECIPE);
        if (mRecipe != null) {
            convertListToMap(Ingredient.class.getSimpleName().concat(SUFFIX_S).toUpperCase(), mRecipe.getIngredients());
            convertListToMap(Step.class.getSimpleName().concat(SUFFIX_S).toUpperCase(), mRecipe.getSteps());
        }

    }

    private <T> void convertListToMap(final String title, List<T> objList) {
        mExpandableListTitle.add(title);
        List<String> values = new ArrayList<>();
        int count = 0;
        for (T obj : objList) {
            if (obj instanceof Ingredient) {
                Ingredient ingredient = ((Ingredient) obj);
                final char SEPARATOR = ' ';
                final String HYPHEN_IN_SPACES = " - ";
                StringBuilder valueBuilder = new StringBuilder();
                valueBuilder.append(FormatUtils.toFormattedStringDecimal(ingredient.getQuantity(), 2));

                valueBuilder.append(SEPARATOR)
                        .append(ingredient.getMeasure());

                valueBuilder.append(HYPHEN_IN_SPACES).append(ingredient.getIngredient());
                values.add(valueBuilder.toString());
            }
            else if (obj instanceof Step) {
                String prefix = String.valueOf(count).concat(". ");
                if (count==0) prefix = "";
                values.add(prefix.concat(((Step) obj).getShortDescription()));
                count++;
            }
        }
        mExpandableListDetail.put(title, values);
    }

    private void initUI(ViewGroup container) {
        mFragRecipeDetailBinding = FragRecipeDetailBinding.inflate(LayoutInflater.from(getContext()), container, false);

        mExpandableListAdapter = new RecipeDetailsExpandableListAdapter(getContext(), mExpandableListTitle, mExpandableListDetail);
        mFragRecipeDetailBinding.expandableListView.setAdapter(mExpandableListAdapter);
        mFragRecipeDetailBinding.expandableListView.expandGroup(STEP_GROUP_POSITION);
        mFragRecipeDetailBinding.expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (groupPosition == STEP_GROUP_POSITION) mCallback.onStepSelected(childPosition);
                return false;
            }
        });
    }
}