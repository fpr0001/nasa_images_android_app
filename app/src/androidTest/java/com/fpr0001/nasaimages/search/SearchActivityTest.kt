package com.fpr0001.nasaimages.search

import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.fpr0001.nasaimages.R
import com.fpr0001.nasaimages.appForTests
import com.fpr0001.nasaimages.di.AppComponentForTest
import com.fpr0001.nasaimages.utils.ResponseRepository
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@MediumTest
class SearchActivityTest {

    @Inject
    lateinit var repository: ResponseRepository

    @get:Rule
    val activityRule = ActivityTestRule(SearchActivity::class.java, false, false)

    @Before
    fun init() {
        appForTests.component.inject(this)
    }

    @Test
    fun test_VisibilityOfViews_When_InitialState() {

//        Mockito.`when`(repository.fetchImages().fetchAndStoreEntities(any())).thenReturn(Completable.error(RuntimeException()))
//        Mockito.doNothing().`when`(splashRouter).goToLogin(any())
        activityRule.launchActivity(null)
//        Espresso.onView(ViewMatchers.withId(R.id.button)).isGone()
        Espresso.onView(ViewMatchers.withId(R.id.progressBar))
//        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}