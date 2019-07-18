package com.fpr0001.nasaimages.search

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.fpr0001.nasaimages.*
import com.fpr0001.nasaimages.apis.NasaApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@MediumTest
class SearchActivityTest {

    @Inject
    lateinit var nasaApi: NasaApi

    @get:Rule
    val activityRule = ActivityTestRule(SearchActivity::class.java, false, false)

    @Before
    fun init() {
        appForTests.component.inject(this)
    }

    @Test
    fun shouldHaveCorrectVisibilityInInitialState() {
        activityRule.launchActivity(null)
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).isInvisible()
        Espresso.onView(ViewMatchers.withId(R.id.emptyListLayout)).isVisible()
        Espresso.onView(ViewMatchers.withText(R.string.type_to_search)).isVisible()
        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).isGone()
    }

    @Test
    fun shouldShowImagesWhenQuerySubmitted() {
        activityRule.launchActivity(null)
        Espresso.onView(ViewMatchers.withId(R.id.action_search)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.search_src_text))
            .perform(
                ViewActions.replaceText("flowers"),
                ViewActions.pressImeActionButton(),
                ViewActions.closeSoftKeyboard()
            )
        Espresso.onView(ViewMatchers.withText(nasaApi.title)).isVisible()
    }
}