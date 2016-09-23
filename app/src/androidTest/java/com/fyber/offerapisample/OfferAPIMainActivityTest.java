package com.fyber.offerapisample;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.fyber.offerapisample.ui.activity.OfferAPIMainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by wonmook on 2016. 9. 23..
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class OfferAPIMainActivityTest {
    @Rule
    public ActivityTestRule<OfferAPIMainActivity> offerAPIMainActivityRule = new ActivityTestRule(OfferAPIMainActivity.class);

    /**
     * Test text related UI with default value.
     */
    @Test
    public void testInitUI() {

        onView(withId(R.id.offer_api_uid)).check(matches(isDisplayed()));
        onView(withId(R.id.offer_api_apikey)).check(matches(isDisplayed()));
        onView(withId(R.id.offer_api_appid)).check(matches(isDisplayed()));
        onView(withId(R.id.offer_api_pup0)).check(matches(isDisplayed()));

        onView(withText(R.string.test_uid)).check(matches(isDisplayed()));
        onView(withText(R.string.test_apikey)).check(matches(isDisplayed()));
        onView(withText(R.string.test_appid)).check(matches(isDisplayed()));
        onView(withText(R.string.test_pub0)).check(matches(isDisplayed()));

    }

    /**
     * Test status of a button to request Offer Mobile API
     */
    @Test
    public void testOfferRequestBtnStatus(){
        onView(withId(R.id.offer_api_request_btn))
                .check(matches(isDisplayed()))
                .check(matches(isEnabled()))
                .check(matches(isClickable()));
    }


    /**
     * Test action of a button to request Offer Mobile API
     */
    @Test
    public void testOfferAPIRequest(){
        onView(withId(R.id.offer_api_request_btn)).perform(click());

    }





}
