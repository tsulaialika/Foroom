package com.example.foroom.pages

import android.view.View
import androidx.test.espresso.matcher.ViewMatchers.hasSibling
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.alternator.foroom.R
import com.example.design_system.R as DesignR
import com.example.design_system.components.image_chooser.ImageChooserItemView
import com.example.design_system.components.image_chooser.ImageChooserListView
import com.example.foroom.data.constants.TestConstants
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RegistrationPage {

    val repeatPasswordInput: Matcher<View> = withId(R.id.repeatPasswordInput)

    val userNameInput: Matcher<View> = allOf(
        withId(R.id.userNameInput),
        hasSibling(repeatPasswordInput)
    )

    val passwordInput: Matcher<View> = allOf(
        withId(R.id.passwordInput),
        hasSibling(repeatPasswordInput)
    )

    val userNameField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(userNameInput)
    )

    val passwordField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(passwordInput)
    )

    val repeatPasswordField: Matcher<View> = allOf(
        withId(DesignR.id.inputEditText),
        isDescendantOfA(repeatPasswordInput)
    )

    val signUpButton: Matcher<View> = allOf(
        withId(R.id.signUpButton),
        hasSibling(repeatPasswordInput)
    )

    val listView: Matcher<View> = withId(R.id.listView)

    val avatarItem: Matcher<View> = allOf(
        isAssignableFrom(ImageChooserItemView::class.java),
        isDescendantOfA(listView)
    )

    val loadedAvatarList: Matcher<View> = allOf(listView, avatarsLoaded())

    val homeContainer: Matcher<View> = withId(R.id.homeContainer)
    val navBar: Matcher<View> = withId(R.id.navBar)

    private fun avatarsLoaded() =
        object : TypeSafeMatcher<View>() {

            override fun describeTo(description: Description) { description.appendText(TestConstants.AVATARS_LOADED_DESCRIPTION)
            }

            override fun matchesSafely(view: View): Boolean {
                return view is ImageChooserListView &&
                        view.isChoosingEnabled &&
                        view.images.isNotEmpty()
            }
        }
}