package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ConversationPage {

    val messagesRecyclerView: Matcher<View> = withId(R.id.messagesRecyclerView)

    private val chatHeader: Matcher<View> = allOf(
        withId(R.id.chatHeaderView),
        hasSibling(messagesRecyclerView)
    )

    private val messageInput: Matcher<View> = withId(R.id.messageInput)

    val messageField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(messageInput)
    )

    val sendMessageButton: Matcher<View> = allOf(
        withId(R.id.sendMessageButton),
        isDescendantOfA(messageInput)
    )

    fun chatTitle(chatName: String): Matcher<View> = allOf(
        withText(chatName),
        isDescendantOfA(chatHeader)
    )

    fun message(
        text: String,
        sender: String
    ): Matcher<View> = allOf(
        withId(R.id.messageView),
        hasDescendant(
            allOf(
                withId(DesignR.id.messageTextView),
                withText(text)
            )
        ),
        hasDescendant(
            allOf(
                withId(DesignR.id.userNameTextView),
                withText(sender)
            )
        )
    )
}