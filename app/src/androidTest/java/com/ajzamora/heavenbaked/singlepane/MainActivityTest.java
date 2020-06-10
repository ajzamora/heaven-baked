package com.ajzamora.heavenbaked.singlepane;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.ajzamora.heavenbaked.R;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String MAIN_TITLE = "HeavenBaked";
    private static final int EXPECTED_ITEMS = 4;
    public static final String EXPECTED_DETAIL_TITLE = "Cheesecake";

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void checkMainTitleBar() {
        onView(allOf(withText(MAIN_TITLE), childAtPosition(allOf(withId(R.id.action_bar),
                childAtPosition(withId(R.id.action_bar_container), 0)), 0),
                isDisplayed())).check(matches(withText(MAIN_TITLE)));
    }

    @Test
    public void checkBakerItems() {
        onView(withId(R.id.rv_recipe_main)).check(new RecyclerViewItemCountAssertion(EXPECTED_ITEMS));
    }

    @Test
    public void clickFourthItem() {
        onView(withId(R.id.rv_recipe_main))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));

        // Checks that the DetailActivity opens with the correct recipe name displayed
        onView(allOf(withText(EXPECTED_DETAIL_TITLE), childAtPosition(allOf(withId(R.id.action_bar),
                childAtPosition(withId(R.id.action_bar_container), 0)), 0),
                isDisplayed())).check(matches(withText(EXPECTED_DETAIL_TITLE)));
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

class RecyclerViewItemCountAssertion implements ViewAssertion {
    private final int expectedCount;

    public RecyclerViewItemCountAssertion(int expectedCount) {
        this.expectedCount = expectedCount;
    }

    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
        if (noViewFoundException != null) {
            throw noViewFoundException;
        }

        RecyclerView recyclerView = (RecyclerView) view;
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        assert adapter != null;
        assertThat(adapter.getItemCount(), is(expectedCount));
    }
}
