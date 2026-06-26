package com.mdi2.todoappch8course107

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mdi2.todoappch8course107.ui.screens.TodoScreen
import com.mdi2.todoappch8course107.ui.theme.ToDoAppCh8Course107Theme

class MainActivity : ComponentActivity() {
    private val _viewModel: TodoViewModel by viewModels ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoAppCh8Course107Theme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                    ){
                        TodoScreen(viewModel = _viewModel)
                }
            }
        }
    }
}

