package com.ajzamora.heavenbaked.singlepane;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.ajzamora.heavenbaked.R;
import com.ajzamora.heavenbaked.ui.DetailActivity;
import com.ajzamora.heavenbaked.ui.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    public static final String EXPECTED_RECIPE_NAME = "Nutella Pie";

    @Rule
    public ActivityTestRule<DetailActivity> mDetailTestRule = new ActivityTestRule<>(DetailActivity.class, false, false);

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void mainClickFirstItem() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);

        onView(withId(R.id.rv_recipe_main))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    @Test
    public void checkDetailTitleBar() {
        // Check Recipe Name
        onView(allOf(withText(EXPECTED_RECIPE_NAME), childAtPosition(allOf(withId(R.id.action_bar),
                childAtPosition(withId(R.id.action_bar_container), 0)), 0),
                isDisplayed())).check(matches(withText(EXPECTED_RECIPE_NAME)));
    }

    // first pane
    @Test
    public void validateViewsIsDisplayed() {
        onView(withId(R.id.expandableListView)).check(matches(isDisplayed()));
    }

    // asserting second pane is not displayed
    @Test
    public void validatePlayerAndDescription() {
        //verticalDrawings
        onView(withId(R.id.verticalDivider)).check(doesNotExist());
        //ExoPlayerView
        onView(withId(R.id.exoPlayerView)).check(doesNotExist());
        //step description textview
        onView(withId(R.id.fr_tv_step_detail)).check(doesNotExist());
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) IdlingRegistry.getInstance().unregister(mIdlingResource);
    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
