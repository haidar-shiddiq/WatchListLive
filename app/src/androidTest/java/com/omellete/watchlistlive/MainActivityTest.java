package com.omellete.watchlistlive;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.action.GeneralLocation;
import androidx.test.espresso.action.GeneralSwipeAction;
import androidx.test.espresso.action.Press;
import androidx.test.espresso.action.Swipe;
import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
    public void A_checkTabs() {
        onView(withId(R.id.viewPager)).check(matches(isDisplayed()));
        onView(withId(R.id.tabs)).check(matches(isDisplayed()));
    }

    @Test
    public void B_loadMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition(19, scrollTo()));
    }

    @Test
    public void C_loadShows() {
        onView(withText("TV SHOWS")).perform(click());
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()));
        onView(withId(R.id.rvTvShows)).perform(actionOnItemAtPosition(19, scrollTo()));
    }

    @Test
    public void D_loadDetailMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void E_loadDetailShows() {
        onView(withText("TV SHOWS")).perform(click());
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()));
        onView(withId(R.id.rvTvShows)).perform(actionOnItemAtPosition(0, click()));
    }

    @Test
    public void F_addMoviesFavorite() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favButton)).check(matches(isDisplayed()));
        onView(withId(R.id.favButton)).perform(click());
    }

    @Test
    public void G_deleteMoviesFavorite() {
        onView(withId(R.id.fav)).check(matches(isDisplayed()));
        onView(withId(R.id.fav)).perform(click());
        onView(withId(R.id.rvFavoriteMovie)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favButton)).check(matches(isDisplayed()));
        onView(withId(R.id.favButton)).perform(click());
    }

    @Test
    public void H_addShowsFavorite() {
        onView(withText("TV SHOWS")).perform(click());
        onView(withId(R.id.rvTvShows)).check(matches(isDisplayed()));
        onView(withId(R.id.rvTvShows)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favButton)).check(matches(isDisplayed()));
        onView(withId(R.id.favButton)).perform(click());
    }

    @Test
    public void I_deleteShowsFavorite() {
        onView(withId(R.id.fav)).check(matches(isDisplayed()));
        onView(withId(R.id.fav)).perform(click());
        onView(withText("TV SHOWS")).perform(click());
        onView(withId(R.id.rvFavoriteShow)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favButton)).check(matches(isDisplayed()));
        onView(withId(R.id.favButton)).perform(click());
    }

    @Test
    public void J_deleteAllFavorite() {
        onView(withId(R.id.fav)).check(matches(isDisplayed()));
        onView(withId(R.id.fav)).perform(click());
        onView(withId(R.id.delAll)).check(matches(isDisplayed()));
        onView(withId(R.id.delAll)).perform(click());
    }

    @Test
    public void K_addToSortFavorite() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition(0, click()));
        onView(withId(R.id.favButton)).check(matches(isDisplayed()));
        onView(withId(R.id.favButton)).perform(click());
    }
    @Test
    public void L_addToSortFavorite() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()));
        onView(withId(R.id.rvMovies)).perform(actionOnItemAtPosition(1, click()));
        onView(withId(R.id.favButton)).check(matches(isDisplayed()));
        onView(withId(R.id.favButton)).perform(click());
    }
    @Test
    public void M_sortFavorite() {
        onView(withId(R.id.fav)).check(matches(isDisplayed()));
        onView(withId(R.id.fav)).perform(click());
        onView(withContentDescription("Button Sort")).check(matches(isDisplayed()));
        onView(withContentDescription("Button Sort")).perform(click());
        onView(withContentDescription("Button Sort")).perform(click());

    }
}