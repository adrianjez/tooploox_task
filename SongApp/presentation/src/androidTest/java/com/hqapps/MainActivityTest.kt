package com.hqapps

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.hqapps.sample_app.R
import com.hqapps.sample_app.presentation.search.MainActivity
import com.hqapps.sample_app.presentation.search.local_search.LocalSearchFragment
import com.hqapps.sample_app.presentation.search.remote_search.RemoteSearchFragment
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest : ActivityTestRule<MainActivity>(MainActivity::class.java) {


    @get:Rule
    var activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp(){
        activityRule.launchActivity(Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER))
    }

    @Test
    fun isInitiallyProperlyDisplayed(){
        onView(withId(R.id.segmentedGroup))
                .check(matches(isDisplayed()))
        onView(withId(R.id.local))
                .check(matches(isChecked()))
        val currentFragment = activityRule.activity.supportFragmentManager.findFragmentById(R.id.fragment_container)
        assertThat<Fragment>(currentFragment, `is`(notNullValue()))
        assertThat<Fragment>(currentFragment, instanceOf(LocalSearchFragment::class.java))
    }

    @Test
    fun isProperlyRemoteDisplayedWhenSelected(){
        onView(withId(R.id.remote))
                .perform(click())
        val currentFragment = activityRule.activity.supportFragmentManager.findFragmentById(R.id.fragment_container)
        assertThat<Fragment>(currentFragment, `is`(notNullValue()))
        assertThat<Fragment>(currentFragment, instanceOf(RemoteSearchFragment::class.java))
    }
}