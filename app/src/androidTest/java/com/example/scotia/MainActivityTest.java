package com.example.scotia;

import android.view.View;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import com.example.scotia.manage.OkHttpProvider;
import com.example.scotia.view.main.MainActivity;
import com.jakewharton.espresso.OkHttp3IdlingResource;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String USER_NAME = "octocat";
    @Rule
    public ActivityTestRule mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void shouldNotChangeInputWhenSearch() throws InterruptedException {
        onView(withId(R.id.et_input))
                .perform(typeText(USER_NAME), closeSoftKeyboard());
        onView(withId(R.id.btn_search)).perform(click());
        onView(withId(R.id.et_input)).check(matches(isDisplayed()));
    }


    @Test
    public void ShouldShowUserName() throws InterruptedException {
        onView(withId(R.id.et_input))
                .perform(typeText(USER_NAME), closeSoftKeyboard());
        onView(withId(R.id.btn_search)).perform(click());
        OkHttp3IdlingResource idlingResource = OkHttp3IdlingResource.create("okhttp",
                OkHttpProvider.getOkHttpInstance());
        Espresso.registerIdlingResources(idlingResource);
        // should not do so. will optimize it if time allow
        Thread.sleep(2000);
        onView(withId(R.id.tv_user_name)).check(matches(withText("The Octocat")));
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void ShouldShowRepos() throws InterruptedException {
        onView(withId(R.id.et_input))
                .perform(typeText(USER_NAME), closeSoftKeyboard());
        onView(withId(R.id.btn_search)).perform(click());
        OkHttp3IdlingResource idlingResource = OkHttp3IdlingResource.create("okhttp",
                OkHttpProvider.getOkHttpInstance());
        Espresso.registerIdlingResources(idlingResource);
        // should not do so. will optimize it when time possible
        Thread.sleep(2000);
        onView(withId(R.id.rv_repos)).check(matches(isDisplayed()));
        Espresso.unregisterIdlingResources(idlingResource);
    }

    @Test
    public void ShouldShowPopupWindow() throws InterruptedException {
        onView(withId(R.id.et_input))
                .perform(typeText(USER_NAME), closeSoftKeyboard());
        onView(withId(R.id.btn_search)).perform(click());
        OkHttp3IdlingResource idlingResource = OkHttp3IdlingResource.create("okhttp",
                OkHttpProvider.getOkHttpInstance());
        Espresso.registerIdlingResources(idlingResource);
        // should not do so. will optimize it when time possible
        Thread.sleep(2000);
        onView(withId(R.id.rv_repos))
                .perform(
                        RecyclerViewActions.actionOnItemAtPosition(
                                1,
                                new ViewAction() {
                                    @Override
                                    public Matcher<View> getConstraints() {
                                        return null;
                                    }

                                    @Override
                                    public String getDescription() {
                                        return "Click on specific button";
                                    }

                                    @Override
                                    public void perform(UiController uiController, View view) {
                                        view.performClick();
                                    }
                                })
                );
        onView(withId(R.id.tv_update_title)).check(matches(isDisplayed()));
        Espresso.unregisterIdlingResources(idlingResource);
    }



}
