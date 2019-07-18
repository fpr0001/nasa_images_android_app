package com.fpr0001.nasaimages.search

import androidx.test.rule.ActivityTestRule
import com.fpr0001.nasaimages.app
import com.fpr0001.nasaimages.appForTests
import com.fpr0001.nasaimages.di.AppComponentForTest
import com.fpr0001.nasaimages.utils.ResponseRepository
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import javax.inject.Inject

class Banana {

    @Inject
    lateinit var r: ResponseRepository
}

class SearchActivityTest2 {

    @get:Rule
    var ruleForDagger = DaggerMock.rule<AppComponentForTest>() {
        set { component -> component.inject(appForTests) }
        customizeBuilder<AppComponentForTest.Builder> { it.provideApplication(appForTests) }
    }

    @Inject
    lateinit var repository: ResponseRepository

    @get:Rule
    val activityRule = ActivityTestRule(SearchActivity::class.java, false, false)

    @Before
    fun init() {
        appForTests.component.ab(this)
    }

    @Test
    fun test_VisibilityOfViews_When_InitialState() {

//        DaggerAppComponentForTest
//            .builder()
//            .provideApplication(app)
//            .
//        Mockito.`when`(repository.fetchImages().fetchAndStoreEntities(any())).thenReturn(Completable.error(RuntimeException()))
//        Mockito.doNothing().`when`(splashRouter).goToLogin(any())
        activityRule.launchActivity(null)
//        Espresso.onView(ViewMatchers.withId(R.id.button)).isGone()
//        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).isGone()
//        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}