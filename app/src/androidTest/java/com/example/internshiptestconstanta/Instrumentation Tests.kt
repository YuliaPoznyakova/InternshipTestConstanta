package com.example.internshiptestconstanta

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.launchActivity
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InstrumentationTests : BaseTest() {

    @Test
    fun `recycler_view_content`() {
        launchFragmentInContainer<FilmFragment>(themeResId = R.style.Theme_InternshipTestConstanta)
        waitForView(withText("Андрей Рублев (1966)")).check(matches(isDisplayed()))
        waitForView(withText("Остров (2006)")).check(matches(isDisplayed()))
    }

    @Test
    fun `dialog_content`() {
        launchActivity<MainActivity>()
        waitForView(withText("Андрей Рублев (1966)")).perform(click())
        waitForView(withText("Фильм Андрей Рублев был нажат"))
            .check(matches(isDisplayed()))
    }
}