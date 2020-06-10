package com.ajzamora.heavenbaked.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.FragmentManager;

import com.ajzamora.heavenbaked.R;
import com.ajzamora.heavenbaked.data.entity.Recipe;
import com.ajzamora.heavenbaked.fragments.RecipeDetailFragment;
import com.ajzamora.heavenbaked.fragments.RecipeStepFragment;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements RecipeDetailFragment.OnStepClickListener {
    public static final String EXTRA_RECIPE = "extra_recipe";
    private boolean mTwoPane;
    private Recipe mRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        if (intent == null) finish(); // close on error

        mRecipe = getIntent().getParcelableExtra(EXTRA_RECIPE);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            if (mRecipe != null) actionBar.setTitle(mRecipe.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (findViewById(R.id.fl_step_container) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
                if (mRecipe != null) {
                    recipeStepFragment.setStepList(mRecipe.getSteps());
                }
                recipeStepFragment.setStepIndex(0);
                fragmentManager.beginTransaction()
                        .add(R.id.fl_step_container, recipeStepFragment)
                        .commit();
            }

        } else {
            mTwoPane = false;
        }
    }

    @Override
    public void onStepSelected(int stepIndex) {
        if (mTwoPane) {
            RecipeStepFragment recipeStepFragment = new RecipeStepFragment();
            recipeStepFragment.setStepList(mRecipe.getSteps());
            recipeStepFragment.setStepIndex(stepIndex);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_step_container, recipeStepFragment)
                    .commit();
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(RecipeStepFragment.EXTRA_STEP, (ArrayList<? extends Parcelable>) mRecipe.getSteps());
            bundle.putInt(RecipeStepFragment.EXTRA_STEP_INDEX, stepIndex);

            final Intent recipeStep = new Intent(this, StepActivity.class);
            recipeStep.putExtras(bundle);
            startActivity(recipeStep);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                navigateUp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void navigateUp() {
        final Intent upIntent = NavUtils.getParentActivityIntent(this);
        if (NavUtils.shouldUpRecreateTask(this, upIntent) || isTaskRoot()) {
            TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
        } else {
            NavUtils.navigateUpTo(this, upIntent);
        }
    }
}
