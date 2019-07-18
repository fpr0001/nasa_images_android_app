package com.fpr0001.nasaimages

import androidx.test.platform.app.InstrumentationRegistry
import com.fpr0001.nasaimages.di.components.AppComponent
import com.fpr0001.nasaimages.di.modules.RepositoryModule
import it.cosenonjaviste.daggermock.DaggerMock
import org.mockito.Mockito

val appForTests: AppForTests
    get() = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as AppForTests