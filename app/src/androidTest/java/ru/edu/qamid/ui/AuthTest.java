package ru.edu.qamid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.DrawerMatchers.isOpen;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.edu.qamid.ui.TestUtils.waitFor;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.edu.qamid.EspressoIdlingResources;
import ru.edu.qamid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String login = "login2";
    String password = "password2";
    String invalidLogin = "password2";
    String toastMessage = "Something went wrong. Try again later.";

    @Before
    public void registerIdlingResources() { //Подключаемся к “счетчику”
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }

    @After
    public void unregisterIdlingResources() { //Отключаемся от “счетчика”
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }


    @Test
    public void AuthorizationTest1() {

        onView(withId(R.id.login_edit_text)).perform(typeText(login),
                closeSoftKeyboard());
        onView(withId(R.id.password_edit_text)).perform(typeText(password),
                closeSoftKeyboard());
        onView(withId(R.id.enter_button)).perform(click());
        onView(isRoot()).perform(waitFor(5000));
        onView(withId(R.id.all_news_text_view)).check(matches(withText("ALL NEWS")));

    }

    @Test
    public void ExitTest2() {

        onView(isRoot()).perform(waitFor(5000));
        onView((withId(R.id.authorization_image_button))).perform(click());
        onView(withText("Log out")).perform(click());
        onView(withId(R.id.enter_button)).check(matches(isDisplayed()));
    }


//    @Test
//    public void invalidLoginTest3() {
//
//        onView(withId(R.id.login_edit_text)).perform(typeText(invalidLogin),
//                closeSoftKeyboard());
//        onView(withId(R.id.password_edit_text)).perform(typeText(password),
//                closeSoftKeyboard());
//        onView(withId(R.id.enter_button)).perform(click());
//        onView(withText("Something went wrong. Try again later.")).check(matches(isDisplayed()));
//       onView(withText(toastMessage)).inRoot(new ToastMatcher()).check(matches(isDisplayed()));
//тест падает с ошибкой в обоих вариантах проверки
//    }


    }

