package com.fpr0001.nasaimages.search

import android.os.SystemClock
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.fpr0001.nasaimages.*
import com.fpr0001.nasaimages.apis.NasaApi
import kotlinx.android.synthetic.main.activity_search.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import javax.inject.Inject


@MediumTest
class SearchActivityTest {

    @Inject
    lateinit var nasaApi: NasaApi

    @Inject
    lateinit var adapter: SearchAdapter

    @get:Rule
    val activityRule = ActivityTestRule(SearchActivity::class.java, false, false)

    @Before
    fun init() {
        appForTests.component.inject(this)
    }

    @Test
    fun shouldHaveCorrectVisibilityInInitialState() {
        activityRule.launchActivity(null)
        onView(withId(R.id.recyclerView)).isInvisible()
        onView(withId(R.id.emptyListLayout)).isVisible()
        onView(ViewMatchers.withText(R.string.type_to_search)).isVisible()
        onView(withId(R.id.progressBar)).isGone()
    }

    @Test
    fun shouldShowImagesWhenQuerySubmitted() {
        activityRule.launchActivity(null)
        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.search_src_text))
            .perform(
                ViewActions.replaceText("flowers"),
                ViewActions.pressImeActionButton(),
                ViewActions.closeSoftKeyboard()
            )
        onView(ViewMatchers.withText(nasaApi.title)).isVisible()
    }

    @Test
    fun shouldRequestNextPageWhenScrolledToBottom() {
        val activity = activityRule.launchActivity(null)
        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.search_src_text))
            .perform(
                ViewActions.replaceText("flowers"),
                ViewActions.pressImeActionButton(),
                ViewActions.closeSoftKeyboard()
            )

        val position = activity.recyclerView.adapter!!.itemCount - 16
        Espresso.onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        verify(adapter).loadMoreFunc
    }
}