package com.mdi2.todoappch8course107.todoApp

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.mdi2.todoappch8course107.TodoViewModel
import com.mdi2.todoappch8course107.ui.screens.TodoScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TodoScreenTest {
    // this rule launches the composable in a test environment
    // tell junit to launch compose before each test
    @get: Rule
    val composeTestRule = createComposeRule()
    private lateinit var _viewModel: TodoViewModel // don't complain about initiating the value

    @Before
    fun setUp() {
        _viewModel = TodoViewModel()

        // set up the composeable with the viewModel -- same as MainActivity does
        composeTestRule.setContent {
            Surface(
                color = MaterialTheme.colorScheme.background
            ) {
                TodoScreen(viewModel = _viewModel)
            }
        }
    }

    @Test
    fun addTask_userTypesAndClicksAdd_taskAppearsInList(){
        //Arrange -- the setUp() already launched TodoScreen with a fresh viewModel
        val validTitle = "Buy Groceries"
        composeTestRule.onNodeWithText(validTitle).assertDoesNotExist()

        // Act
        composeTestRule
            .onNodeWithTag("input_field")
            .performTextInput(validTitle)

        // click on the add button
        composeTestRule
            .onNodeWithTag("add_button")
            .performClick()

    // Assert - verify the task does not exist yet
        composeTestRule
            .onNodeWithText(validTitle)
            .assertIsDisplayed()
    }

    @Test
    fun addTask_inputField_clearAfterAdding(){
        val validTitle = "Buy Groceries"
        composeTestRule
            .onNodeWithTag("input_field")
            .performTextInput(validTitle)

        composeTestRule
            .onNodeWithTag("add_button")
            .performClick()

        composeTestRule
            .onNodeWithTag("input_field")
            .assert(hasText(""))
    }

    @Test
    fun addTask_inputField_counterIncrease(){
        val validTitle = "Buy Groceries"

        composeTestRule.onNodeWithText("0 task(s)").assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("input_field")
            .performTextInput(validTitle)

        composeTestRule
            .onNodeWithTag("add_button")
            .performClick()

        composeTestRule.onNodeWithText("1 task(s)").assertIsDisplayed()
    }
}