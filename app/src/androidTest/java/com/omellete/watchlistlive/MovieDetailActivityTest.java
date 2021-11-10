package com.omellete.watchlistlive;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.omellete.watchlistlive.data.WatchlistEntity.TYPE_MOVIE;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.omellete.watchlistlive.data.WatchlistEntity;
import com.omellete.watchlistlive.ui.detail.DetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovieDetailActivityTest {

    private final WatchlistEntity localMovies = LocalData.getDummyMovie();

    @Rule
    public ActivityTestRule<DetailActivity> activityRule = new ActivityTestRule<DetailActivity>(DetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailActivity.class);
            result.putExtra(DetailActivity.EXTRA_ID, localMovies.getId());
            result.putExtra(DetailActivity.EXTRA_TYPE, TYPE_MOVIE);
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void A_loadMovie() {

        onView(withId(R.id.imgPhoto)).check(matches(isDisplayed()));
        onView(withId(R.id.imgBackdrop)).check(matches(isDisplayed()));
        onView(withId(R.id.nameOri)).check(matches(isDisplayed()));
        onView(withId(R.id.nameOri)).check(matches(withText(localMovies.getTitleOri())));
        onView(withId(R.id.tvName)).check(matches(isDisplayed()));
        onView(withId(R.id.tvName)).check(matches(withText(localMovies.getName())));
        onView(withId(R.id.tvGenres)).check(matches(isDisplayed()));
        onView(withId(R.id.tvGenres)).check(matches(withText(localMovies.getGenres())));
        onView(withId(R.id.tvDescription)).perform(scrollTo());
        onView(withId(R.id.tvDescription)).check(matches(isDisplayed()));
        onView(withId(R.id.tvDescription)).check(matches(withText(localMovies.getDescription())));
        onView(withId(R.id.tvYear)).check(matches(isDisplayed()));
        onView(withId(R.id.tvYear)).check(matches(withText(String.valueOf(localMovies.getYear()))));
        onView(withId(R.id.userVote)).check(matches(isDisplayed()));
        onView(withId(R.id.userVote)).check(matches(withText(localMovies.getVote())));
    }

    @Test
    public void B_testShare() {
        onView(withId(R.id.share)).check(matches(isDisplayed()));
        onView(withId(R.id.share)).perform(click());
    }

}