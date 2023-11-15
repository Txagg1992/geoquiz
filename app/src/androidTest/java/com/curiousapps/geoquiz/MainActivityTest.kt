package com.curiousapps.geoquiz

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>
    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun showFirstQuestionOnLaunch(){
        onView(withId(R.id.info_text))
            .check(matches(withText(R.string.question_australia)))
    }
    @Test
    fun showTrueButtonOnLaunch(){
        onView(withId(R.id.true_button))
            .check(matches(withText(R.string.true_button_text)))
    }
    @Test
    fun showTrueButtonOnLaunchAndIsClickable(){
        onView(withId(R.id.true_button))
            .check(matches(withText(R.string.true_button_text)))
            .perform(click())
    }
    @Test
    fun showFalseButtonOnLaunch(){
        onView(withId(R.id.false_button))
            .check(matches(withText(R.string.false_button_text)))
    }
    @Test
    fun showFalseButtonOnLaunchAndIsClickable(){
        onView(withId(R.id.false_button))
            .check(matches(withText(R.string.false_button_text)))
            .perform(click())
    }
    @Test
    fun showPreviousButtonOnLaunch(){
        onView(withId(R.id.false_button))
            .check(matches(withText(R.string.false_button_text)))
    }
    @Test
    fun showPreviousButtonOnLaunchAndIsClickable(){
        onView(withId(R.id.false_button))
            .check(matches(withText(R.string.false_button_text)))
            .perform(click())
    }
    @Test
    fun showNextButtonOnLaunch(){
        onView(withId(R.id.false_button))
            .check(matches(withText(R.string.false_button_text)))
    }
    @Test
    fun showNextButtonOnLaunchAndIsClickable(){
        onView(withId(R.id.false_button))
            .check(matches(withText(R.string.false_button_text)))
            .perform(click())
    }
    @Test
    fun showCheatButtonOnLaunch(){
        onView(withId(R.id.cheat_button))
            .check(matches(withText(R.string.cheat_button_text)))
    }
    @Test
    fun showCheatButtonOnLaunchAndIsClickable(){
        onView(withId(R.id.cheat_button))
            .check(matches(withText(R.string.cheat_button_text)))
            .perform(click())
    }
    @Test
    fun showSecondQuestionOnNextButtonPress(){
        onView(withId(R.id.next_button))
            .perform(click())
        onView(withId(R.id.info_text))
            .check(matches(withText(R.string.question_ocean)))
    }

    @Test
    fun handleActivityRecreation(){
        onView(withId(R.id.next_button))
            .perform(click())
        scenario.recreate()
        onView(withId(R.id.info_text))
            .check(matches(withText(R.string.question_ocean)))
    }
}