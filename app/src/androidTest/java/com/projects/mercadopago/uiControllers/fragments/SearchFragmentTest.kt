package com.projects.mercadopago.uiControllers.fragments

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.projects.mercadopago.R
import com.projects.mercadopago.uiControllers.activities.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class SearchFragmentTest{
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Test
    fun happyPath() {
        ActivityScenario.launch(MainActivity::class.java)

        // Check Buttons fragment screen is displayed
        Espresso.onView(ViewMatchers.withId(R.id.clearAllButton))
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