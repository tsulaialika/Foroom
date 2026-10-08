package com.example.foroom.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.foroom.data.constants.TestData
import com.example.foroom.presentation.ui.activity.ForoomActivity
import com.example.foroom.presentation.ui.util.datastore.user.ForoomUserDataStore
import com.example.foroom.steps.ChatSteps
import com.example.foroom.steps.ConversationSteps
import com.example.foroom.steps.LoginSteps
import com.example.foroom.steps.ProfileSteps
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext

@RunWith(AndroidJUnit4::class)
class ConversationTests {

    @get:Rule(order = 0)
    val clearSessionRule = object : ExternalResource() {

        override fun before() = runBlocking {
            GlobalContext.get()
                .get<ForoomUserDataStore>()
                .clearUserData()
        }
    }

    @get:Rule(order = 1)
    val activityRule = ActivityScenarioRule(ForoomActivity::class.java)

    private val loginSteps = LoginSteps()
    private val profileSteps = ProfileSteps()
    private val chatSteps = ChatSteps()
    private val conversationSteps = ConversationSteps()

    @Test
    fun sendMessageAndVerifyPersistence() {
        loginAsUserA()
        openChatAndVerify(TestData.JOHN_WEEK_CHAT)

        val message = TestData.uniqueMessage(TestData.DRINK_INVITATION)

        conversationSteps.sendMessageAndVerify(message)

        chatSteps.closeChat()
        openChatAndVerify(TestData.JOHN_WEEK_CHAT)

        conversationSteps.verifyMessageDisplayed(
            message = message,
            sender = TestData.USER_A_USERNAME
        )
    }

    @Test
    fun sendAcademyQuestion() {
        loginAsUserA()
        openChatAndVerify(TestData.ACADEMY_CHAT_NAME)

        val question = TestData.uniqueMessage(TestData.ACADEMY_PRACTICE_QUESTION)

        conversationSteps.sendMessageAndVerify(question)
    }

    @Test
    fun exchangeMessagesBetweenUsers() {
        loginAsUserA()
        openChatAndVerify(TestData.SHARED_CHAT_NAME)

        val greeting = TestData.uniqueMessage(TestData.WEEKEND_GREETING)

        conversationSteps.sendMessageAndVerify(greeting)

        repeat(TestData.CONVERSATION_HISTORY_MESSAGE_COUNT) { index ->
            conversationSteps.sendMessage(TestData.historyMessage(index))
        }

        conversationSteps.verifyMessageDisplayed(
            message = TestData.historyMessage(
                TestData.CONVERSATION_HISTORY_MESSAGE_COUNT - 1
            ),
            sender = TestData.USER_A_USERNAME
        )

        chatSteps.closeChat()
        switchToUserB()

        openChatAndVerify(TestData.SHARED_CHAT_NAME)

        conversationSteps.swipeToOlderMessage(
            message = greeting,
            sender = TestData.USER_A_USERNAME
        )

        val reply = TestData.uniqueMessage(TestData.WEEKEND_REPLY)

        conversationSteps.sendMessageAndVerify(reply)

        chatSteps.closeChat()
        switchToUserA()

        openChatAndVerify(TestData.SHARED_CHAT_NAME)

        conversationSteps.verifyMessageDisplayed(
            message = reply,
            sender = TestData.USER_B_USERNAME
        )
    }

    private fun loginAsUserA() {
        loginSteps.loginWithCredentials(
            username = TestData.USER_A_USERNAME,
            password = TestData.USER_A_PASSWORD
        )

        conversationSteps.setCurrentUser(TestData.USER_A_USERNAME)
    }

    private fun loginAsUserB() {
        loginSteps.loginWithCredentials(
            username = TestData.USER_B_USERNAME,
            password = TestData.USER_B_PASSWORD
        )

        conversationSteps.setCurrentUser(TestData.USER_B_USERNAME)
    }

    private fun openChatAndVerify(chatName: String) {
        chatSteps
            .searchChat(chatName)
            .verifyChatListItemDisplayed(chatName)
            .openChatAndVerify(chatName)

        conversationSteps
            .verifyConversationDisplayed()
            .verifyChatTitleDisplayed(chatName)
    }

    private fun switchToUserB() {
        profileSteps.openProfile()
        profileSteps.signOut()

        loginAsUserB()
    }

    private fun switchToUserA() {
        profileSteps.openProfile()
        profileSteps.signOut()

        loginAsUserA()
    }
}