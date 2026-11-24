package ru.edu.qamid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.edu.qamid.ui.TestUtils.waitFor;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.edu.qamid.EspressoIdlingResources;
import ru.edu.qamid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditEspressoTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    String login = "login2";
    String password = "password2";

    @Before
    public void registerIdlingResources() {
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }
    @After
    public void unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }
    @Test
    public void editEspressoTest() {
//        ViewInteraction textInputEditText = onView(
//                allOf(withId(R.id.login_edit_text),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.login_text_input_layout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText.perform(replaceText("login2"), closeSoftKeyboard());
//
//        ViewInteraction textInputEditText2 = onView(
//                allOf(withId(R.id.password_edit_text),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.password_text_input_layout),
//                                        0),
//                                0),
//                        isDisplayed()));
//        textInputEditText2.perform(replaceText("password2"), closeSoftKeyboard());
//
//        ViewInteraction materialButton = onView(
//                allOf(withId(R.id.enter_button), withText("Sign in"), withContentDescription("Save"),
//                        childAtPosition(
//                                allOf(withId(R.id.enter_button_container),
//                                        childAtPosition(
//                                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
//                                                2)),
//                                0),
//                        isDisplayed()));
//        materialButton.perform(click());
//
//        ViewInteraction materialTextView = onView(
//                allOf(withId(R.id.all_news_text_view), withText("All news"),
//                        childAtPosition(
//                                allOf(withId(R.id.main_news_list_container),
//                                        childAtPosition(
//                                                withClassName(is("android.widget.LinearLayout")),
//                                                0)),
//                                1),
//                        isDisplayed()));
//        materialTextView.perform(click());
////
//        ViewInteraction materialButton2 = onView(
//                allOf(withId(R.id.news_edit_button), withContentDescription("News editing button"),
//                        childAtPosition(
//                                childAtPosition(
//                                        withId(R.id.news_list_container),
//                                        0),
//                                3),
//                        isDisplayed()));
//        materialButton2.perform(click());

        onView(withId(R.id.login_edit_text)).perform(typeText(login),
                closeSoftKeyboard());

        onView(withId(R.id.password_edit_text)).perform(typeText(password),
                closeSoftKeyboard());

        onView(withId(R.id.enter_button)).perform(click());

        onView(isRoot()).perform(waitFor(5000));
//переходим в all news
        onView(withId(R.id.all_news_text_view)).perform(click());
//нажимаем добавить кнопка "карандаш"
        onView(withId(R.id.news_edit_button)).perform(click());


//нажимаем на редактировать
        ViewInteraction appCompatImageView = onView(
                allOf(withId(R.id.news_item_edit_image_view), withContentDescription("News editing button"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_item_material_card_view),
                                        0),
                                15),
                        isDisplayed()));
        appCompatImageView.perform(click());

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.news_description_edit_text), withText("Test description"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_description_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Test1"));

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.news_description_edit_text), withText("Test1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.news_description_text_input_layout),
                                        0),
                                0),
                        isDisplayed()));
        textInputEditText4.perform(closeSoftKeyboard());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.news_save_button), withText("Save"), withContentDescription("Save"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("com.google.android.material.card.MaterialCardView")),
                                        0),
                                6)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction recyclerView = onView(
                allOf(withId(R.id.news_list_recycler_view),
                        childAtPosition(
                                withClassName(is("androidx.constraintlayout.widget.ConstraintLayout")),
                                0)));
        recyclerView.perform(actionOnItemAtPosition(0, click()));

        ViewInteraction textView = onView(
                allOf(withId(R.id.news_item_description_text_view), withText("Test1"),
                        withParent(withParent(withId(R.id.news_item_material_card_view))),
                        isDisplayed()));
        textView.check(matches(withText("Test1")));
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
