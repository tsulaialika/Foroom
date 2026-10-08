package com.example.foroom.data.constants

object TestData {

    // Android 3 test data
    const val PROFILE_TEST_USERNAME = "lika_tsulaia"
    const val PROFILE_TEST_PASSWORD = "LikaTest123!"
    const val UPDATED_PASSWORD = "LikaTest456!"

    const val CHAT_NAME_PREFIX = "Lika Tsulaia"

    fun generateUniqueChatName(): String =
        "$CHAT_NAME_PREFIX ${System.currentTimeMillis()}"

    // Android 4 test accounts
    const val USER_A_USERNAME = "RedLika"
    const val USER_A_PASSWORD = "LVibe123!"

    const val USER_B_USERNAME = "JustLika"
    const val USER_B_PASSWORD = "LUcky789!"

    // Prepared chats
    const val JOHN_WEEK_CHAT = "johnWeek"
    const val ACADEMY_CHAT_NAME = "Lika Tsulaia's QA Corner"
    const val SHARED_CHAT_NAME = "something"

    // Scenario 1
    const val DRINK_INVITATION = "How about getting a drink together after work this week?"

    // Scenario 2
    const val ACADEMY_PRACTICE_QUESTION = "Which Automation Academy topic has been the most interesting for you so far?"

    // Scenario 3
    const val WEEKEND_GREETING = "Hey! Are you free sometime this weekend?"
    const val WEEKEND_REPLY = "I think so! What did you have in mind?"

    // Used to create enough conversation history for the swipe requirement.
    const val CONVERSATION_HISTORY_MESSAGE_COUNT = 25
    const val CONVERSATION_HISTORY_MESSAGE_PREFIX = "Conversation history message"

    fun uniqueMessage(message: String): String =
        "$message [${System.currentTimeMillis()}]"

    fun historyMessage(index: Int): String =
        "$CONVERSATION_HISTORY_MESSAGE_PREFIX ${index + 1}"
}