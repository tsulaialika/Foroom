package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.constants.TestConstants
import com.example.foroom.pages.LoginPage

class LoginSteps {

    private val loginPage = LoginPage()

    fun verifyLoginScreenDisplayed() = apply {
        onView(loginPage.logInButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun enterUsername(username: String) = apply {
        onView(loginPage.userNameField)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .input(username)
    }

    fun enterPassword(password: String) = apply {
        onView(loginPage.passwordField)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .input(password)
    }

    fun clickLogin() = apply {
        onView(loginPage.logInButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .tap()
    }

    fun verifyHomeScreenDisplayed() = apply {
        onView(loginPage.homeContainer)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))

        onView(loginPage.navBar)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    // Full login flow, reused by all three scenarios
    fun loginWithCredentials(username: String, password: String) = apply {
        verifyLoginScreenDisplayed()
        enterUsername(username)
        enterPassword(password)
        clickLogin()
        verifyHomeScreenDisplayed()
    }

}