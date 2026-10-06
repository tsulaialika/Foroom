package com.example.foroom.data.constants

object TestData {

    const val PROFILE_TEST_USERNAME = "lika_tsulaia"
    const val PROFILE_TEST_PASSWORD = "LikaTest123!"
    const val UPDATED_PASSWORD = "LikaTest456!"

    const val CHAT_NAME_PREFIX = "Lika Tsulaia"

    fun generateUniqueChatName(): String =
        "$CHAT_NAME_PREFIX ${System.currentTimeMillis()}"

}