package com.projects.mercadopago

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.projects.mercadopago.uiControllers.activities.MainActivity
import com.projects.mercadopago.uiControllers.fragments.SearchFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ExampleInstrumentedTest {

    @get:Rule
    var hiltRule= HiltAndroidRule(this)

//    @Test
//    fun useAppContext() {
//        // Context of the app under test.
//        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//        assertEquals("com.projects.mercadopago", appContext.packageName)
//    }

    @Test
    fun happyPath() {
//        ActivityScenario.launch(MainActivity::class.java)

        FragmentScenario.launch(SearchFragment::class.java)

        // Check Buttons fragment screen is displayed
        Espresso.onView(withId(R.id.clearAllButton))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

//        // Tap on Button 1
//        Espresso.onView(withId(R.id.clearAllButton)).perform(ViewActions.click())
//
//        // Navigate to Logs screen
//        Espresso.onView(withId(R.id.clearAllButton)).perform(ViewActions.click())
//
//        // Check Logs fragment screen is displayed
//        Espresso.onView(ViewMatchers.withText(Matchers.containsString("Interaction with 'Button 1'")))
//            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}