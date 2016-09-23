package com.fyber.offerapisample;

import android.os.SystemClock;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.FragmentTransaction;
import android.test.suitebuilder.annotation.LargeTest;

import com.fyber.offerapisample.model.OfferAPIResponse;
import com.fyber.offerapisample.ui.activity.OfferAPIMainActivity;
import com.fyber.offerapisample.ui.fragment.OfferAPIResultFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;

/**
 * Created by wonmook on 2016. 9. 23..
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class OfferAPIResultFragmentTest {

    private static String OK = "OK";
    private static String ERROR_INVALID_HASHKEY = "ERROR_INVALID_HASHKEY";
    private static String ERROR_INVALID_APPID   = "ERROR_INVALID_APPID";
    @Rule
    public ActivityTestRule<OfferAPIMainActivity> offerAPIMainActivityRule = new ActivityTestRule(OfferAPIMainActivity.class);

    /**
     * Test inital UI status within OfferAPIResultFragment
     * At first, next and before button have to be disabled.
     */
    @Test
    public void testInitFragmentStatus(){
        showOfferAPIResultFragment();
        checkDefaultPagingBtnStatus();
    }

    /**
     * Test a situation Offer API return valid response and show listview of offers.
     */
    @Test
    public void testResultOKCode(){
        showOfferAPIResultFragment();

        onView(withId(R.id.offer_api_request_btn)).perform(click());
        Espresso.closeSoftKeyboard();
        SystemClock.sleep(500);

        onView(withId(R.id.offer_result_list)).check(matches(isDisplayed()));
        onView(withText(offerAPIMainActivityRule.getActivity().getString(R.string.offer_response_code_txt)+OK))
                .check(matches(isDisplayed()));

    }

    /**
     * Test a situation when the hashkey is invalid by requesting API with invalid apikey.
     */
    @Test
    public void testResultInvalidHashCode(){
        showOfferAPIResultFragment();

        onView(allOf(withClassName(endsWith("EditText")), withId(R.id.offer_api_apikey))).perform(replaceText("1111"));

        onView(withId(R.id.offer_api_request_btn)).perform(click());
        Espresso.closeSoftKeyboard();
        SystemClock.sleep(500);

        onView(withText(offerAPIMainActivityRule.getActivity().getString(R.string.offer_response_code_txt) + ERROR_INVALID_HASHKEY))
                .check(matches(isDisplayed()));

        checkDefaultPagingBtnStatus();
    }

    /**
     * Test a situation when the Appid is invalid by requesting API with invalid appid.
     */
    @Test
    public void testResultInvalidAppIdCode(){
        showOfferAPIResultFragment();

        onView(allOf(withClassName(endsWith("EditText")), withId(R.id.offer_api_appid))).perform(replaceText("1111"));

        onView(withId(R.id.offer_api_request_btn)).perform(click());
        Espresso.closeSoftKeyboard();
        SystemClock.sleep(500);

        onView(withText(offerAPIMainActivityRule.getActivity().getString(R.string.offer_response_code_txt)+ERROR_INVALID_APPID))
                .check(matches(isDisplayed()));
        checkDefaultPagingBtnStatus();
    }

    /**
     * Check paging buttons work well.
     */
    @Test
    public void testPagingBtn() {
        showOfferAPIResultFragment();

        onView(withId(R.id.offer_api_request_btn)).perform(click());
        Espresso.closeSoftKeyboard();
        SystemClock.sleep(500);

        onView(withId(R.id.offer_result_list)).check(matches(isDisplayed()));

        onView(withId(R.id.offer_next_btn)).perform(click());
        onView(withId(R.id.offer_before_btn)).perform(click());
    }

    /**
     * Check item of listview of offer is shown well.
     */
    @Test
    public void testOfferItemUI(){
        showOfferAPIResultFragment();

        onView(withId(R.id.offer_api_request_btn)).perform(click());
        Espresso.closeSoftKeyboard();
        SystemClock.sleep(500);


        onView(withId(R.id.offer_result_list)).check(matches(isDisplayed()));
        onData(allOf(is(instanceOf(OfferAPIResponse.class)), withId(R.id.offer_title)));
        onData(allOf(is(instanceOf(OfferAPIResponse.class)), withId(R.id.offer_teaser)));
        onData(allOf(is(instanceOf(OfferAPIResponse.class)), withId(R.id.offer_payout)));
        onData(allOf(is(instanceOf(OfferAPIResponse.class)), withId(R.id.offer_thumbnail)));
    }

    /**
     * Check offer item click action works well.
     */
    @Test
    public void testOfferItemSelectAction(){
        openDetailActivity();
    }


    /**
     * Check default Ui of detail activity is shown well.
     */
    @Test
    public void testInitUIOfDetailActivity() {

        openDetailActivity();
        SystemClock.sleep(500);
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_teaser)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_payout)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_thumbnail)).check(matches(isDisplayed()));
        onView(withId(R.id.close_btn)).check(matches(isDisplayed()));
    }

    /**
     * Check close detail activity click action works well.
     */
    @Test
    public void testOfferDetailCloseAction(){
        openDetailActivity();
        SystemClock.sleep(500);
        onView(withId(R.id.close_btn)).perform(click());

    }

    private void openDetailActivity(){
        showOfferAPIResultFragment();

        onView(withId(R.id.offer_api_request_btn)).perform(click());
        Espresso.closeSoftKeyboard();
        SystemClock.sleep(500);

        onView(withId(R.id.offer_result_list)).check(matches(isDisplayed()));
        onData(anything()).inAdapterView(withId(R.id.offer_result_list)).atPosition(0).perform(click());
    }

    private void checkDefaultPagingBtnStatus(){
        onView(withId(R.id.offer_before_btn))
                .check(matches(isDisplayed()))
                .check(matches(not(isEnabled())));

        onView(withId(R.id.offer_next_btn))
                .check(matches(isDisplayed()))
                .check(matches(not(isEnabled())));
    }

    //Open Offer API Result List Fragment
    private OfferAPIResultFragment showOfferAPIResultFragment(){
        FragmentTransaction ft = offerAPIMainActivityRule.getActivity().getSupportFragmentManager().beginTransaction();
        OfferAPIResultFragment resultFragment = (OfferAPIResultFragment)offerAPIMainActivityRule.getActivity().getSupportFragmentManager().findFragmentById(R.id.offer_api_result_fragment);
        ft.replace(R.id.offer_api_result_fragment, resultFragment);
        ft.commit();
        return resultFragment;
    }




}
