package com.example.foroom.data.constants

object TestData {

    const val VALID_USERNAME = "student"
    const val VALID_PASSWORD = "Student123!"
    const val INVALID_PASSWORD = "DefinitelyNotMyPass!"
    const val NON_EXISTENT_USERNAME = "UserWhoNeverExisted"

    const val INVALID_USERNAME_ERROR = "Username does not exist"
    const val INVALID_PASSWORD_ERROR = "Incorrect password"

    fun generateUniqueUsername(): String =
        "LikaTest${System.currentTimeMillis()}"
}