package com.fpr0001.nasaimages

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.fpr0001.nasaimages.search.SearchActivity
import com.fpr0001.nasaimages.utils.ResponseRepositoryImpl
import com.fpr0001.nasaimages.*
import it.cosenonjaviste.daggermock.DaggerMock
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

@MediumTest
@RunWith(AndroidJUnit4::class)
class SplashActivityTest {

    @get:Rule
    var ruleForDagger = DaggerMock.rule<AppComponentForTest>(AppModuleForTest()) {
        set { component -> component.inject(app) }
        customizeBuilder<AppComponentForTest.Builder> { it.provideApplication(app) }
    }

    @Mock
    lateinit var repository: ResponseRepositoryImpl

    @get:Rule
    val activityRule = ActivityTestRule(SearchActivity::class.java, false, false)

    @Test
    fun test_VisibilityOfViews_When_InitialState() {

        activityRule.activity

//        Mockito.`when`(repository.fetchImages().fetchAndStoreEntities(any())).thenReturn(Completable.error(RuntimeException()))
//        Mockito.doNothing().`when`(splashRouter).goToLogin(any())
        activityRule.launchActivity(null)
//        Espresso.onView(ViewMatchers.withId(R.id.button)).isGone()
//        Espresso.onView(ViewMatchers.withId(R.id.progressBar)).isGone()
//        Espresso.onView(ViewMatchers.withId(R.id.imageView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}
