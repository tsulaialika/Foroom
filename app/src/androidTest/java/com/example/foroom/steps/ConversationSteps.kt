package com.example.foroom.steps

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.isViewDisplayed
import com.example.foroom.Helper.swiper
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.constants.TestConstants
import com.example.foroom.pages.ConversationPage
import org.hamcrest.Matcher

class ConversationSteps {

    private val conversationPage = ConversationPage()

    private var currentUser: String? = null

    fun setCurrentUser(username: String) = apply {
        currentUser = username
    }

    fun verifyConversationDisplayed() = apply {
        onView(conversationPage.messagesRecyclerView)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun verifyChatTitleDisplayed(chatName: String) = apply {
        onView(conversationPage.chatTitle(chatName))
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun sendMessage(message: String) = apply {
        onView(conversationPage.messageField)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .input(message)

        onView(conversationPage.sendMessageButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
            .tap()
    }

    fun verifyMessageDisplayed(
        message: String,
        sender: String
    ) = apply {
        onView(conversationPage.message(message, sender))
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun sendMessageAndVerify(message: String) = apply {
        sendMessage(message)

        verifyMessageDisplayed(
            message = message,
            sender = requireCurrentUser()
        )
    }

    fun swipeToOlderMessage(
        message: String,
        sender: String
    ) = apply {

        repeat(TestConstants.MAX_CONVERSATION_SWIPE_ATTEMPTS) {

            if (conversationPage
                    .message(message, sender)
                    .isViewDisplayed()
            ) {
                return@apply
            }

            swipeDownMessagesList()
        }

        verifyMessageDisplayed(message, sender)
    }


    private fun requireCurrentUser(): String =
        requireNotNull(currentUser) {
            "Current user must be set before sending and verifying a message."
        }

    private fun swipeDownMessagesList() {
        val (top, bottom) = messagesListBounds()
        val height = bottom - top

        require(height > 0) {
            "Messages list must have a positive height before swiping."
        }

        swiper(
            start = top + height / 4,
            end = bottom - height / 4,
            delay = 300
        )
    }

    private fun messagesListBounds(): Pair<Int, Int> {
        var top = 0
        var bottom = 0

        onView(conversationPage.messagesRecyclerView)
            .perform(object : ViewAction {

                override fun getConstraints(): Matcher<View> =
                    isDisplayed()

                override fun getDescription(): String =
                    "get visible messages list bounds"

                override fun perform(
                    uiController: UiController,
                    view: View
                ) {
                    val location = IntArray(2)

                    view.getLocationOnScreen(location)

                    top = location[1]
                    bottom = location[1] + view.height
                }
            })

        return top to bottom
    }
}