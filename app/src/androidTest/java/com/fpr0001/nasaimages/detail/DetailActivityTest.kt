package com.fpr0001.nasaimages.detail

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.filters.MediumTest
import androidx.test.rule.ActivityTestRule
import com.fpr0001.nasaimages.*
import com.fpr0001.nasaimages.apis.NasaApi
import com.fpr0001.nasaimages.detail.DetailActivity.Companion.EXTRA_MODEL
import com.fpr0001.nasaimages.di.modules.NasaApiImplForTests
import com.fpr0001.nasaimages.di.modules.ResponseMapperForTests
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@MediumTest
class DetailActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(DetailActivity::class.java, false, false)

    @Inject
    lateinit var nasaApi: NasaApiImplForTests

    @Inject
    lateinit var mapper: ResponseMapperForTests

    @Before
    fun init() {
        appForTests.component.inject(this)
    }

    @Test
    fun shouldChangeLayoutsWhenRotated() {
        val pojo = mapper.fromItemResponse(nasaApi.pojo.collection!!.items!!.first())!!
        val activity =  activityRule.launchActivity(Intent().apply { putExtra(EXTRA_MODEL, pojo) })
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Espresso.onView(ViewMatchers.withText(pojo.description)).isVisible()
        Espresso.onView(ViewMatchers.withText(pojo.title)).isVisible()
        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        Espresso.onView(ViewMatchers.withText(pojo.description)).check(doesNotExist())
        Espresso.onView(ViewMatchers.withText(pojo.title)).check(doesNotExist())
        Espresso.onView(ViewMatchers.withId(R.id.imageView)).isVisible()
    }
}