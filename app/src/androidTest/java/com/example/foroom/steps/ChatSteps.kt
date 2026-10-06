package com.example.foroom.steps

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.example.foroom.Helper.input
import com.example.foroom.Helper.tap
import com.example.foroom.Helper.waitUntilVisible
import com.example.foroom.data.constants.TestConstants
import com.example.foroom.pages.ChatsPage
import com.example.foroom.pages.CreateChatPage

class ChatSteps {

    private val chatsPage = ChatsPage()
    private val createChatPage = CreateChatPage()

    // Create chat
    fun openCreateChat() = apply {
        onView(chatsPage.createChatNavigation)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .tap()

        onView(createChatPage.chatNameField)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun enterChatName(chatName: String) = apply {
        onView(createChatPage.chatNameField)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .input(chatName)
    }

    fun selectChatImage(position: Int = TestConstants.DEFAULT_CHAT_IMAGE_POSITION) = apply {
        onView(createChatPage.readyImageChooser)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)

        createChatPage.chatImageItem.tap(byPosition = position)
    }

    fun clickCreateChat() = apply {
        onView(createChatPage.createChatButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .tap()
    }

    // Opened chat screen
    fun verifyChatScreenDisplayed(chatName: String) = apply {
        onView(chatsPage.openedChatTitle(chatName))
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    fun closeChat() = apply {
        onView(chatsPage.leaveChatButton)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .tap()

        onView(chatsPage.chatsRecyclerView)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

    // Chat list
    fun searchChat(chatName: String) = apply {
        onView(chatsPage.searchChatField)
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .input(chatName)
    }

    fun verifyChatListItemDisplayed(chatName: String) = apply {
        onView(chatsPage.chatListItem(chatName))
            .waitUntilVisible(TestConstants.DEFAULT_TIMEOUT)
            .check(matches(isDisplayed()))
    }

}