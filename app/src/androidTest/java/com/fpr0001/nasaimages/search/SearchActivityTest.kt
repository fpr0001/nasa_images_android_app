package com.fpr0001.nasaimages.search

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.SystemClock
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
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
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf


@MediumTest
class SearchActivityTest {

    private val someQuery = "Workday!"

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
        onView(withText(R.string.type_to_search)).isVisible()
        onView(withId(R.id.progressBar)).isGone()
    }

    @Test
    fun shouldShowImagesWhenQuerySubmitted() {
        activityRule.launchActivity(null)
        searchWithSomeQuery()
        onView(withText(nasaApi.title)).isVisible()
    }

    @Test
    fun shouldRequestNextPageWhenScrolledToBottom() {
        val activity = activityRule.launchActivity(null)
        searchWithSomeQuery()
        val position = activity.recyclerView.adapter!!.itemCount - 16
        Espresso.onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        verify(adapter).loadMoreFunc
        assert(adapter.list.size > 130)
    }

    @Test
    fun shouldStartDetailActivityWhenViewHolderTapped() {
        activityRule.launchActivity(null)
        searchWithSomeQuery()
        Espresso.onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        val imageData = adapter.list.first()
        onView(withText(imageData.description)).isVisible()
        onView(withText(imageData.title)).isVisible()
        onView(withId(R.id.textViewDate)).isVisible()
    }

    private fun searchWithSomeQuery() {
        onView(withId(R.id.action_search)).perform(click())
        onView(withId(R.id.search_src_text))
            .perform(
                ViewActions.replaceText(someQuery),
                ViewActions.pressImeActionButton(),
                ViewActions.closeSoftKeyboard()
            )
    }

    @Test
    fun shouldKeepSearchStateInConfigurationChange() {
        val activity = activityRule.launchActivity(null)
        searchWithSomeQuery()
        rotateScreen(activity)
        onView(withText(someQuery)).check(matches(isDisplayed()))
    }

    @Test
    fun shouldKeepListStateInConfigurationChange() {
        val activity = activityRule.launchActivity(null)
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        searchWithSomeQuery()
        val position = activity.recyclerView.adapter!!.itemCount - 16
        val visiblePosition = position - 3
        val pojoAtVisiblePosition = adapter.list[visiblePosition]
        onView(withText(pojoAtVisiblePosition.title)).check(matches(isDisplayed()))
        Espresso.onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(position))
        val itemCount = activity.recyclerView.adapter!!.itemCount
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        assert(activity.recyclerView.adapter!!.itemCount == itemCount)
        onView(withId(R.id.recyclerView))
            .check(
                matches(
                    withViewAtPosition(
                        visiblePosition,
                        hasDescendant(allOf(withText(pojoAtVisiblePosition.title), isDisplayed()))
                    )
                )
            )
    }

    private fun rotateScreen(activity: Activity) {
        activity.requestedOrientation =
            if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            else
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun withViewAtPosition(position: Int, itemMatcher: Matcher<View>): Matcher<View> {
        return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                itemMatcher.describeTo(description)
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
            }
        }
    }
}

