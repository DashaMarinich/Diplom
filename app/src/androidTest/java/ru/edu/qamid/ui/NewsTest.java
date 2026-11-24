package ru.edu.qamid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static kotlinx.coroutines.flow.FlowKt.first;
import static ru.edu.qamid.ui.DeleteEspressoNewsTest.childAtPosition;
import static ru.edu.qamid.ui.TestUtils.waitFor;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
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

public class NewsTest {
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
    public void MainNewsTest8() {
//        onView(withId(R.id.login_edit_text)).perform(typeText(login),
//                closeSoftKeyboard());
//        onView(withId(R.id.password_edit_text)).perform(typeText(password),
//                closeSoftKeyboard());
//        onView(withId(R.id.enter_button)).perform(click());

        onView(isRoot()).perform(waitFor(5000));

        onView(withId(R.id.all_news_text_view)).perform(click());

        onView(withText("News")).check(matches(isDisplayed()));
    }

    @Test
    public void AddNewsTest10() {
        onView(isRoot()).perform(waitFor(5000));
//переходим в all news
        onView(withId(R.id.all_news_text_view)).perform(click());
//нажимаем добавить кнопка "карандаш"
        onView(withId(R.id.news_edit_button)).perform(click());
//нажимаем на кнопкку "плюс"
        onView(withId(R.id.add_news_image_view)).perform(click());
//нажимаем на кнопку развернуть список категории
        onView(withId(R.id.news_category_auto_complete)).perform(click());
//выбрать категорию день рождения
        onView(withText("День рождения"))
                .inRoot(isPlatformPopup())
                .perform(click());
//нажимаем на дату
        onView(withId(R.id.news_publish_date_edit_text)).perform(click());
//ОК
        onView(withId(android.R.id.button1))
                .inRoot(isDialog())
                .perform(click());
//нажимаем на время
        onView(withId(R.id.news_publish_time_edit_text)).perform(click());
//ОК
        onView(withId(android.R.id.button1))
                .inRoot(isDialog())
                .perform(click());
//заполняем описание
        onView(withId(R.id.news_description_edit_text)).perform(replaceText("День рождения01"));
//нажмаем сохранить
        onView(withId(R.id.news_save_button)).perform(click());
//нажимаем развернуть
        onView(allOf(
                withId(R.id.news_item_expand_image_view),
                isDescendantOfA(childAtPosition(withId(R.id.news_list_recycler_view), 0))
        )).perform(click());
//проверка по первой карточке
        onView(allOf(
                withId(R.id.news_item_description_text_view),
                withText("День рождения01"),
                isDescendantOfA(childAtPosition(withId(R.id.news_list_recycler_view), 0))
        )).check(matches(isDisplayed()));

   }

    @Test
    public void EditNewsTest11() {

        onView(isRoot()).perform(waitFor(5000));
//переходим в all news
        onView(withId(R.id.all_news_text_view)).perform(click());
//нажимаем добавить кнопка "карандаш"
        onView(withId(R.id.news_edit_button)).perform(click());
//нажимаем на редактировать новость
        onView(allOf(
                withId(R.id.news_item_edit_image_view),
                isDescendantOfA(childAtPosition(withId(R.id.news_list_recycler_view), 0))
        )).perform(click());
//заменяем описание
        onView(withId(R.id.news_description_edit_text)).perform(replaceText("Edit Test"),
                closeSoftKeyboard());
        onView(withId(R.id.news_save_button)).perform(click());
//нажимаем развернуть
        onView(allOf(
                withId(R.id.news_item_expand_image_view),
                isDescendantOfA(childAtPosition(withId(R.id.news_list_recycler_view), 0))
        )).perform(click());
//проверка по первой карточке
        onView(allOf(
                withId(R.id.news_item_description_text_view),
                withText("Edit Test"),
                isDescendantOfA(childAtPosition(withId(R.id.news_list_recycler_view), 0))
        )).check(matches(isDisplayed()));

    }

//    news_sort_button ID кнопка сортировка
//    news_filter_button ID кнопка фильтр
    @Test
    public void sortNewsTest18() {

        onView(isRoot()).perform(waitFor(5000));
//переходим в all news
        onView(withId(R.id.all_news_text_view)).perform(click());
// проверяем кликабельность
        onView(withId(R.id.news_sort_button)).check(matches(isClickable()));

    }

    @Test
    public void filterNewsTest19() {

        onView(isRoot()).perform(waitFor(5000));

        onView(withId(R.id.all_news_text_view)).perform(click());

        onView(withId(R.id.news_filter_button)).perform(click());

        onView(withId(R.id.filter_news_category_text_input_layout)).perform(click());

        onView(withText("Объявление"))
                .inRoot(isPlatformPopup())
                .perform(click());

        onView((withId(R.id.filter_news_apply_button))).perform(click());

        onView(withText("Объявление")).check(matches(isDisplayed()));
    }
//    id	filter_news_category_text_input_layout категория
//    id	filter_news_apply_button фильтр


    @Test
    public void DeleteNewsTest9() {
        onView(isRoot()).perform(waitFor(5000));
        //переходим в all news
        onView(withId(R.id.all_news_text_view)).perform(click());
//нажимаем добавить кнопка "карандаш"
        onView(withId(R.id.news_edit_button)).perform(click());
//проверяем, что кнопка удаления кликабельна
        onView(allOf(
                withId(R.id.news_item_delete_image_view),
                isDescendantOfA(childAtPosition(withId(R.id.news_list_recycler_view), 0))
        )).check(matches(isClickable()));
    }

    @Test
    public void tabsTest22() {

        onView(isRoot()).perform(waitFor(5000));
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("News")).perform(click());
        onView(withText("News")).check(matches(isDisplayed()));
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText("Main")).perform(click());
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
        onView(withId(R.id.our_mission_image_button)).perform(click());
        onView(withId(R.id.our_mission_title_text_view)).check(matches(isDisplayed()));
    }
}