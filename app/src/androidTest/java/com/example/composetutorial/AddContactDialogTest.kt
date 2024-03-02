package com.example.composetutorial

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddContactDialogTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun addContactDialogDisplayed() {
        val testState = ContactState(
            contacts = listOf(
                Contact(1, "John", "Doe", "1234567890"),
                Contact(2, "Jane", "Smith", "0987654321")
            ),
            isAddingContact = true
        )

        composeTestRule.setContent {
            AddContactDialog(state = testState, onEvent = {})
        }

        composeTestRule.onNodeWithTag("first_name_field").assertIsDisplayed()
        composeTestRule.onNodeWithTag("last_name_field").assertIsDisplayed()
        composeTestRule.onNodeWithTag("phone_field").assertIsDisplayed()
        composeTestRule.onNodeWithText("Save").assertIsDisplayed()
    }

    @Test
    fun typeInFields() {
        val testState = ContactState(
            contacts = listOf(
                Contact(1, "John", "Doe", "1234567890"),
                Contact(2, "Jane", "Smith", "0987654321")
            ),
            isAddingContact = true,
            firstName = "John",
            lastName = "Doe",
            phoneNumber = "1234567890"
        )

        composeTestRule.setContent {
            AddContactDialog(state = testState, onEvent = {})
        }

        composeTestRule.onNodeWithTag("first_name_field").assertTextEquals("John")
        composeTestRule.onNodeWithTag("last_name_field").assertTextEquals("Doe")
        composeTestRule.onNodeWithTag("phone_field").assertTextEquals("1234567890")
    }
}
