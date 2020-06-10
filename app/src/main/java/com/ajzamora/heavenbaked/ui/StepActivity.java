package com.ajzamora.heavenbaked.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.ajzamora.heavenbaked.R;
import com.ajzamora.heavenbaked.data.Step;
import com.ajzamora.heavenbaked.fragments.RecipeStepFragment;

import java.util.List;

public class StepActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getSupportActionBar().hide();
        }
        else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        // Only create new fragments when there is no previously saved state
        if(savedInstanceState == null) {
            RecipeStepFragment recipeStepFragment = new RecipeStepFragment();

            List<Step> stepList = getIntent().getParcelableArrayListExtra(RecipeStepFragment.EXTRA_STEP);
            int stepIndex = getIntent().getIntExtra(RecipeStepFragment.EXTRA_STEP_INDEX, 0);
            recipeStepFragment.setStepList(stepList);
            recipeStepFragment.setStepIndex(stepIndex);

            actionBar.setTitle(stepList.get(stepIndex).getShortDescription());

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.fl_step_container, recipeStepFragment)
                    .commit();
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
