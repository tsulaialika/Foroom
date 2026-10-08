package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.constants.AppLanguage
import com.example.foroom.data.constants.TestConstants
import com.example.foroom.pages.ChangeLanguagePage
import com.example.foroom.pages.ChangePasswordPage
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.LoginPage
import com.example.foroom.pages.ProfilePage

class ProfileSteps {

    private val chatsPage = ChatsPage()
    private val profilePage = ProfilePage()
    private val changePasswordPage = ChangePasswordPage()
    private val changeLanguagePage = ChangeLanguagePage()
    private val loginPage = LoginPage()

    fun openProfile() = apply {
        onView(chatsPage.profileNavigation)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .tap()

        onView(profilePage.changePasswordItem)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun openChangePassword() = apply {
        onView(profilePage.changePasswordItem)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .tap()

        onView(changePasswordPage.submitPasswordButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun fillPasswordForm(password: String) = apply {
        onView(changePasswordPage.passwordField)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .input(password)

        onView(changePasswordPage.repeatPasswordField)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .input(password)
    }

    fun confirmPasswordChange() = apply {
        onView(changePasswordPage.submitPasswordButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
            .tap()
    }

    fun changePasswordTo(newPassword: String) = apply {
        openProfile()
        openChangePassword()
        fillPasswordForm(newPassword)
        confirmPasswordChange()
    }

    fun switchLanguage(language: AppLanguage) = apply {
        onView(profilePage.changeLanguageItem)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .tap()

        val option = when (language) {
            AppLanguage.GEORGIAN ->
                changeLanguagePage.geoLanguageOption

            AppLanguage.ENGLISH ->
                changeLanguagePage.engLanguageOption
        }

        onView(option)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .tap()
    }

    fun verifyLanguageApplied(language: AppLanguage) = apply {
        onView(profilePage.changeLanguageLabel(language.changeLanguageLabel))
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun signOut() = apply {
        onView(profilePage.signOutItem)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
            .tap()

        onView(loginPage.logInButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }
}