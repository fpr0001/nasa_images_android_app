package com.fpr0001.nasaimages

import androidx.test.platform.app.InstrumentationRegistry
import com.fpr0001.nasaimages.di.components.AppComponent
import com.fpr0001.nasaimages.di.modules.RepositoryModule
import it.cosenonjaviste.daggermock.DaggerMock
import org.mockito.Mockito


fun espressoDaggerMockRule() = DaggerMock.rule<AppComponent>() {
    set { component -> component.inject(app) }
    customizeBuilder<AppComponent.Builder> { it.provideApplication(app) }
}

val app: App
    get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as App

val appForTests: AppForTests
    get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as AppForTests


fun <T> any(): T {
    Mockito.any<T>()
    return uninitialized()
}

@Suppress("UNCHECKED_CAST")
private fun <T> uninitialized(): T = null as T