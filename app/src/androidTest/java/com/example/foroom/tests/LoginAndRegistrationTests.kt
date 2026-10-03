package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.constants.TestData
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.RegistrationSteps
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class LoginAndRegistrationTests {

    @Before
    fun clearUserData() = runBlocking {
        GlobalContext
            .get()
            .get<ForoomUserDataStore>()
            .clearUserData()
    }

    @get:Rule
    val activityRule =
        ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val registrationSteps = RegistrationSteps()

    @Test
    fun loginWithInvalidPassword() {
        loginSteps
            .verifyLoginScreenDisplayed()
            .enterUsername(TestData.VALID_USERNAME)
            .enterPassword(TestData.INVALID_PASSWORD)
            .clickLogin()
            .verifyPasswordError(TestData.INVALID_PASSWORD_ERROR)
            .verifyUsernameErrorNotDisplayed()
    }

    @Test
    fun loginWithInvalidUser() {
        loginSteps
            .verifyLoginScreenDisplayed()
            .enterUsername(TestData.NON_EXISTENT_USERNAME)
            .enterPassword(TestData.INVALID_PASSWORD)
            .clickLogin()
            .verifyUsernameError(TestData.INVALID_USERNAME_ERROR)
            .verifyPasswordError(TestData.INVALID_PASSWORD_ERROR)
    }

    @Test
    fun registerNewUser() {
        val username = TestData.generateUniqueUsername()

        loginSteps
            .verifyLoginScreenDisplayed()
            .clickSignUp()

        registrationSteps
            .verifyRegistrationScreenDisplayed()
            .enterUsername(username)
            .enterPassword(TestData.VALID_PASSWORD)
            .enterRepeatPassword(TestData.VALID_PASSWORD)
            .selectAvatar()
            .clickSignUp()
            .verifyHomeScreenDisplayed()
    }
}