package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matcher

class ChangePasswordPage {

    val passwordInput: Matcher<View> = withId(R.id.passwordInput)
    val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)

    val passwordField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(passwordInput)
    )

    val repeatPasswordField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(repeatPasswordInput)
    )

    val submitPasswordButton: Matcher<View> = withId(DesignR.id.actionButton)

}