package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class LoginPage {

    val userNameInput: Matcher<View> = withId(R.id.userNameInput)
    val passwordInput: Matcher<View> = withId(R.id.passwordInput)

    val userNameField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(userNameInput)
    )

    val passwordField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(passwordInput)
    )

    val logInButton: Matcher<View> = withId(R.id.logInButton)
    val signUpButton: Matcher<View> = withId(R.id.signUpButton)

    val userNameError: Matcher<View> = allOf(
        withId(DesignR.id.descriptionTextView),
        isDescendantOfA(userNameInput),
        isDisplayed()
    )

    val passwordError: Matcher<View> = allOf(
        withId(DesignR.id.descriptionTextView),
        isDescendantOfA(passwordInput),
        isDisplayed()
    )
}