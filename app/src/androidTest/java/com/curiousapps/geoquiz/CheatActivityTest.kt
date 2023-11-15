package com.curiousapps.geoquiz

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CheatActivityTest {
    private lateinit var cheatScenario: ActivityScenario<CheatActivity>

    @Before
    fun setUp() {
        cheatScenario = ActivityScenario.launch(CheatActivity::class.java)
    }

    @After
    fun tearDown() {
        cheatScenario.close()
    }

    @Test
    fun showShowAnswerButtonOnLaunch(){
        onView(withId(R.id.show_answer_button))
            .check(matches(withText(R.string.show_answer_button_text)))
            .perform(click())
    }

    @Test
    fun isShowAnswerTextViewAvailable(){
        onView(withId(R.id.answer_text_view))
    }

    @Test
    fun isCheatWarningShown(){
        onView(withId(R.id.cheat_warning))
            .check(matches(withText(R.string.warning_text)))
    }
}