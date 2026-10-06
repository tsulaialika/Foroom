package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.constants.AppLanguage
import com.example.foroom.data.constants.TestData
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ProfileAndChatTests {

    // Every test starts logged out, so no test depends on another one
    @get:Rule(order = 0)
    val clearSessionRule = object : ExternalResource() {
        override fun before() = runBlocking {
            GlobalContext.get().get<ForoomUserDataStore>().clearUserData()
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()

    @Test
    fun userCanLoginWithNewPasswordAfterChangingIt() {
        var passwordChanged = false

        logInWithCurrentPassword()

        try {
            profileSteps
                .openProfile()
                .openChangePassword()
                .fillPasswordForm(TestData.UPDATED_PASSWORD)
                .confirmPasswordChange()
            passwordChanged = true

            // the app returns to the login screen automatically after Confirm
            loginSteps.verifyLoginScreenDisplayed()

            // a successful login with the new password confirms the change
            loginSteps.loginWithCredentials(
                TestData.PROFILE_TEST_USERNAME,
                TestData.UPDATED_PASSWORD
            )
        } finally {
            if (passwordChanged) restoreOriginalPassword()
        }
    }

    @Test
    fun userCanSwitchLanguageBackAndForth() {
        logInWithCurrentPassword()

        profileSteps
            .openProfile()
            .switchLanguage(AppLanguage.GEORGIAN)
            .verifyLanguageApplied(AppLanguage.GEORGIAN)
            .switchLanguage(AppLanguage.ENGLISH)
            .verifyLanguageApplied(AppLanguage.ENGLISH)
            .switchLanguage(AppLanguage.GEORGIAN)
            .verifyLanguageApplied(AppLanguage.GEORGIAN)
    }

    @Test
    fun userCanCreateChatAndFindItViaSearch() {
        val chatName = TestData.generateUniqueChatName()

        logInWithCurrentPassword()

        chatSteps
            .openCreateChat()
            .enterChatName(chatName)
            .selectChatImage()
            .clickCreateChat()
            .verifyChatScreenDisplayed(chatName)
            .closeChat()
            .searchChat(chatName)
            .verifyChatListItemDisplayed(chatName)
    }

    private fun logInWithCurrentPassword() {
        loginSteps.loginWithCredentials(
            TestData.PROFILE_TEST_USERNAME,
            TestData.PROFILE_TEST_PASSWORD
        )
    }

    // Puts the account back to its known password so the next run starts clean
    private fun restoreOriginalPassword() {
        profileSteps.changePasswordTo(TestData.PROFILE_TEST_PASSWORD)
        loginSteps.verifyLoginScreenDisplayed()
    }

}