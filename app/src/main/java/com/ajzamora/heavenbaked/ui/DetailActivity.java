package com.ajzamora.heavenbaked.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.ajzamora.heavenbaked.R;
import com.ajzamora.heavenbaked.fragments.RecipeStepFragment;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_RECIPE = "extra_recipe";
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (findViewById(R.id.fr_step_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
                fragmentManager.beginTransaction()
                        .add(R.id.fr_step_container, recipeStepFragment)
                        .commit();
            }

        } else {
            mTwoPane = false;
        }
    }
}
