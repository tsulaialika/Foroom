package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
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

        onView(loginPage.signUpButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun enterUsername(username: String) = apply {
        onView(loginPage.userNameField)
            .input(username)
    }

    fun enterPassword(password: String) = apply {
        onView(loginPage.passwordField)
            .input(password)
    }

    fun clickLogin() = apply {
        onView(loginPage.logInButton)
            .tap()
    }

    fun clickSignUp() = apply {
        onView(loginPage.signUpButton)
            .tap()
    }

    fun verifyUsernameError(expectedMessage: String) = apply {
        onView(loginPage.userNameError)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(withText(expectedMessage)))
    }

    fun verifyPasswordError(expectedMessage: String) = apply {
        onView(loginPage.passwordError)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(withText(expectedMessage)))
    }

    fun verifyUsernameErrorNotDisplayed() = apply {
        onView(loginPage.userNameError)
            .check(doesNotExist())
    }
}