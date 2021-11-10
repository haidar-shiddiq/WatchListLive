package com.omellete.watchlistlive;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MainActivityTest {

    @Rule
    public ActivityTestRule activityRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void A_loadMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition(19, scrollTo()));
    }

    @Test
    public void B_loadShows() {
        onView(withText("TV SHOWS")).perform(click());
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()));
        onView(withId(R.id.rvTvShows)).perform(actionOnItemAtPosition(19, scrollTo()));
    }

    @Test
    public void C_loadDetailMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void D_loadDetailShows() {
        onView(withText("TV SHOWS")).perform(click());
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()));
        onView(withId(R.id.rvTvShows)).perform(actionOnItemAtPosition(0, click()));
    }

}