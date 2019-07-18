package com.fpr0001.nasaimages.search

import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.fpr0001.nasaimages.utils.ResponseRepository
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject


@MediumTest
class SplashActivityTest {

//    @get:Rule
//    var ruleForDagger = DaggerMock.rule<AppComponentForTest>() {
//        set { component -> component.inject(app) }
//        customizeBuilder<AppComponentForTest.Builder> { it.provideApplication(app) }
//    }

    @Inject
    lateinit var repository: ResponseRepository

    @get:Rule
    val activityRule = ActivityTestRule(SearchActivity::class.java, false, false)

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