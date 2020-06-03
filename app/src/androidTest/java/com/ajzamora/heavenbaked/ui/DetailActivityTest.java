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
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<DetailActivity> activityTestRule
            = new ActivityTestRule<>(DetailActivity.class);

    @Test
    public void testDetailShows() {
        onView(withId(R.id.fr_detail)).check(matches(isDisplayed()));
    }

    @Test
    public void testPlayerShows() {
        onView(withId(R.id.exoPlayerView)).check(matches(isDisplayed()));
    }

    @Test
    public void verticalDrawings() {
        onView(withId(R.id.verticalDivider)).check(matches(isDisplayed()));
    }

    @Test
    public void testContainerShows() {
        onView(withId(R.id.fl_step_container)).check(matches(isDisplayed()));
    }

}