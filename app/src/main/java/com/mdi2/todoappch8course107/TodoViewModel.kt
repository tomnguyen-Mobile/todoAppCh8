package com.mdi2.todoappch8course107
// previous step create Task.kt class
// Second Step create view model: TodoViewModel.kt
// Next step add Screens > TodoScreen.kt

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    private var _nextId = 1

    val tasks: List<Task> get() = _tasks

    // challenge create the function addTask() here
    // title should not be blank
    fun addTask(title:String){
        if (title.isNotBlank()) {
            _tasks.add( Task( id=_nextId++, title=title.trim() ) )
        }
    }

    fun removeTask( taskId: Int ){
        _tasks.removeAll{ it.id == taskId}
    }

    fun getTaskCount():Int = _tasks.size

    fun containsTask( title:String ): Boolean = _tasks.any { it.title == title }
}