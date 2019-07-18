package com.fpr0001.nasaimages

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry

val appForTests: AppForTests
    get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as AppForTests

fun ViewInteraction.isGone() = getViewAssertion(ViewMatchers.Visibility.GONE)

fun ViewInteraction.isVisible() = getViewAssertion(ViewMatchers.Visibility.VISIBLE)

fun ViewInteraction.isInvisible() = getViewAssertion(ViewMatchers.Visibility.INVISIBLE)

private fun ViewInteraction.getViewAssertion(visibility: ViewMatchers.Visibility): ViewInteraction {
    return this.check(ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility)))
}
