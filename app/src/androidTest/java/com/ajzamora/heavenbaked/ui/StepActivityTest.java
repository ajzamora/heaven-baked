package com.ajzamora.heavenbaked.ui;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.ajzamora.heavenbaked.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class StepActivityTest {

    @Rule
    public ActivityTestRule<StepActivity> activityTestRule
            = new ActivityTestRule<>(StepActivity.class);

    @Test
    public void testStepShows() {
        onView(withId(R.id.fr_tv_step_detail)).check(matches(isDisplayed()));
    }

    @Test
    public void testPlayerShows() {
        onView(withId(R.id.exoPlayerView)).check(matches(isDisplayed()));
    }



}