package com.mdi2.todoappch8course107.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.keepScreenOn
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mdi2.todoappch8course107.Task
import com.mdi2.todoappch8course107.TodoViewModel

// previous TodoViewModel.kt
// create TodoScreen.kt
// next

@Composable
fun TodoScreen(
    viewModel: TodoViewModel
    ){
    var inputText by remember { mutableStateOf( value="") }
    Column( // Q: is there a way to adjust the automatic fill?
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "My Todo List",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom=16.dp)
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Enter a task")},
                modifier = Modifier
                    .weight(1f)
                    .testTag("input_field"),
                singleLine = true
            )

            Spacer(Modifier.width(8.dp))

            Button(
                onClick = {
                    viewModel.addTask(title=inputText)
                    inputText = ""
                }, // end of onclick
                modifier = Modifier.testTag("add_button")
                ){
                Text("Add")
            }// end of button
        }// end of row

        Spacer(modifier = Modifier.height(16.dp))

        // Task counter showing the number of tasks in the list
        Text(
            text = "${viewModel.tasks.size} task(s)",
            style = MaterialTheme.typography.displayLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        LazyColumn( modifier = Modifier.fillMaxSize() ){
            items(
                items = viewModel.tasks,
                key = { task -> task.id }
            ){ task ->
                TaskItem(
                    task = task,
                    onDelete = { viewModel.removeTask(task.id)}
                )
            }
        }// end of lazy column
    }// end of column
}

@Composable
fun TaskItem(
    task: Task,
    onDelete: () -> Unit,
    ){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical=4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
            ){
            Text (
                text = task.title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .weight(1f)

            )// end of Text

            IconButton( onClick = onDelete){
                Icon(
                    imageVector = Icons.Default.Delete, // need to add the extension for the icon
                    contentDescription = "Delete Task"
                )
            }// end of IconButton
        } // end of row

    }// end of card
}


@Preview(showBackground = true)
@Composable
fun TodoScreenPreview(){
    TodoScreen(viewModel = TodoViewModel() )
}