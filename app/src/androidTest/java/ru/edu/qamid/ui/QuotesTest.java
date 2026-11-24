package ru.edu.qamid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.edu.qamid.ui.TestUtils.waitFor;

import androidx.test.espresso.IdlingRegistry;
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
public class QuotesTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String login = "login2";
    String password = "password2";

    @Before
    public void registerIdlingResources() { //Подключаемся к “счетчику”
        IdlingRegistry.getInstance().register(EspressoIdlingResources.idlingResource);
    }
    @After
    public void unregisterIdlingResources() { //Отключаемся от “счетчика”
        IdlingRegistry.getInstance().unregister(EspressoIdlingResources.idlingResource);
    }

    @Test
    public void quotesTest6() {
        onView(withId(R.id.login_edit_text)).perform(typeText(login),
                closeSoftKeyboard());
        onView(withId(R.id.password_edit_text)).perform(typeText(password),
                closeSoftKeyboard());
        onView(withId(R.id.enter_button)).perform(click());
        onView(isRoot()).perform(waitFor(5000));
//нажимаем на переход во вкладку цитаты
        onView(withId(R.id.our_mission_image_button)).perform(click());
//открывается страница с цитатами
        onView(withId(R.id.our_mission_title_text_view)).check(matches(withText("Love is all")));
    }

    @Test
    public void expandQuoteTest7() {

//        onView(withId(R.id.login_edit_text)).perform(typeText(login),
//                closeSoftKeyboard());
//        onView(withId(R.id.password_edit_text)).perform(typeText(password),
//                closeSoftKeyboard());
//        onView(withId(R.id.enter_button)).perform(click());
        onView(isRoot()).perform(waitFor(5000));
//переходим в цитаты
        onView(withId(R.id.our_mission_image_button)).perform(click());
//нажимаем на первую цитату
        onView(withId(R.id.our_mission_item_list_recycler_view)).
                perform(actionOnItemAtPosition(0, click()));
//проверяем по тексту
        onView(withText("\"Ну, идеальное устройство мира в моих глазах. Где никто не оценивает, никто не осудит, где говоришь, и тебя слышат, где, если страшно, тебя обнимут и возьмут за руку, а если холодно тебя согреют.” Юля Капис, волонтер")).check(matches(isDisplayed()));




    }
}

