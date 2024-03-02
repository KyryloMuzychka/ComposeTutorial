package com.example.composetutorial

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ContactScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun contactScreenTest() {
        val contacts = listOf(
            Contact(1,"John", "Doe", "1234567890"),
            Contact(2,"Jane", "Doe", "0987654321")
        )
        val state = ContactState(contacts)

        composeTestRule.setContent {
            MaterialTheme {
                Surface {
                    ContactScreen(state = state, onEvent = {})
                }
            }
        }

        contacts.forEach { contact ->
            composeTestRule.onNodeWithText("${contact.firstName} ${contact.lastName}").assertExists()
            composeTestRule.onNodeWithText(contact.phoneNumber).assertExists()
        }

        SortType.values().forEach { sortType ->
            composeTestRule.onNodeWithText(sortType.name).assertExists()
        }
    }
}
