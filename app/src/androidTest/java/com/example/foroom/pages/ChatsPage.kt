package com.example.foroom.pages

import android.view.View
import android.widget.EditText
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ChatsPage {

    // Home navigation
    val createChatNavigation: Matcher<View> = withId(R.id.homeNavigationCreateChat)
    val profileNavigation: Matcher<View> = withId(R.id.homeNavigationProfile)

    // Opened chat screen
    private val messagesRecyclerView: Matcher<View> = withId(R.id.messagesRecyclerView)

    val chatHeader: Matcher<View> = allOf(
        withId(R.id.chatHeaderView),
        hasSibling(messagesRecyclerView)
    )

    val leaveChatButton: Matcher<View> = allOf(
        withId(R.id.closeButton),
        hasSibling(messagesRecyclerView)
    )

    fun openedChatTitle(chatName: String): Matcher<View> = allOf(
        withText(chatName),
        isDescendantOfA(chatHeader)
    )

    // Chat list
    val searchChatInput: Matcher<View> = withId(R.id.searchChatInput)

    val searchChatField: Matcher<View> = allOf(
        isAssignableFrom(EditText::class.java),
        isDescendantOfA(searchChatInput)
    )

    val chatsRecyclerView: Matcher<View> = withId(R.id.chatsRecyclerView)

    fun chatListItem(chatName: String): Matcher<View> = allOf(
        withId(DesignR.id.chatTitleTextView),
        withText(chatName),
        isDescendantOfA(chatsRecyclerView)
    )

}