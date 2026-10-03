package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.constants.TestConstants
import com.example.foroom.pages.RegistrationPage

class RegistrationSteps {

    private val registrationPage = RegistrationPage()

    fun verifyRegistrationScreenDisplayed() = apply {
        onView(registrationPage.repeatPasswordInput)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun enterUsername(username: String) = apply {
        onView(registrationPage.userNameField)
            .input(username)
    }

    fun enterPassword(password: String) = apply {
        onView(registrationPage.passwordField)
            .input(password)
    }

    fun enterRepeatPassword(password: String) = apply {
        onView(registrationPage.repeatPasswordField)
            .input(password)
    }

    fun selectAvatar() = apply {
        onView(registrationPage.loadedAvatarList)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)

        registrationPage.avatarItem.tap(
            byPosition = TestConstants.DEFAULT_AVATAR_POSITION
        )
    }

    fun clickSignUp() = apply {
        onView(registrationPage.signUpButton)
            .tap()
    }

    fun verifyHomeScreenDisplayed() = apply {
        onView(registrationPage.homeContainer)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))

        onView(registrationPage.navBar)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }
}