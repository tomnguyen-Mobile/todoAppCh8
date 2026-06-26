package com.mdi2.todoappch8course107.todoApp

import androidx.activity.viewModels
import com.mdi2.todoappch8course107.TodoViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import kotlin.getValue

class TodoViewModelTest {
    // The system under test recreate before each test
    private lateinit var _viewModel: TodoViewModel // don't complain about initiating the value

    @Before
    fun setUp(){
        _viewModel = TodoViewModel()
    }

    @Test
    fun addTask_withValidTitle_appearsInList(){ // name of test, what we are testing,expected result
        // Arrange - the setUp() already created an empty ViewModel
        // Act -- call the function that we want to test
        val validTitle = "Buy Groceries"
        _viewModel.addTask(title = validTitle)
        // Assert - check the result
        assertEquals( 1, _viewModel.getTaskCount() ) // expect the result to be, the actual value that the code produced
        assertTrue(_viewModel.containsTask(validTitle))
    } // end of test function

    @Test
    fun addTask_withBlankTitle_isIgnored(){
        _viewModel.addTask(title="     ")
        assertEquals( 0, _viewModel.getTaskCount() )
        assertFalse(_viewModel.containsTask("     "))
    }

    @Test
    fun addTask_withEmptyString_isIgnored(){
        _viewModel.addTask(title="")
        assertEquals( 0, _viewModel.getTaskCount() )
        assertFalse(_viewModel.containsTask(""))
    }

    @Test
    fun addTask_withTrailingSpace_isTrimmed(){
        _viewModel.addTask(title="Reply on Discussion 02     ")
        assertTrue(_viewModel.containsTask("Reply on Discussion 02"))
    }

    @Test
    fun removeTask_withValidId_removesTask(){
        // Arrange -- add a task to the list so that we can remove it
        _viewModel.addTask("value 1")
        val taskId = _viewModel.tasks[0].id

        //Act -- call the function we want to test
        _viewModel.removeTask(taskId)

        // Assert -- check the result
        assertEquals( 0, _viewModel.getTaskCount() )
    }

    @Test
    fun removeTask_withInvalidId_doesNothing(){
        // Arrange -- add a task to the list so that we can remove it
        _viewModel.addTask("value 1")
        val invalidTaskId = _viewModel.tasks[0].id + 1

        //Act -- call the function we want to test
        _viewModel.removeTask(invalidTaskId)

        // Assert -- check the result
        assertEquals( 1, _viewModel.getTaskCount() )
    }
}