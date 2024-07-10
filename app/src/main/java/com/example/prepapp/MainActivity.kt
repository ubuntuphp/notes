package com.example.prepapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.prepapp.local.NotesModel
import com.example.prepapp.ui.theme.PrepAppTheme
import com.example.prepapp.viewmodels.NotesViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: NotesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application).create(NotesViewModel::class.java)
        enableEdgeToEdge()
        setContent {
            PrepAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainView()
                }
            }
        }

        loadData()
    }

    private fun loadData() {
    }

    @Composable
    private fun MainView() {
        Box(modifier = Modifier.padding(top = 50.dp)) {
            Text(text = "time: ${viewModel.getLiveCount().collectAsState(initial = 0).value}")
            Image(modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { viewModel.clearAllNotes() },
                painter = painterResource(id = R.drawable.ic_check), contentDescription = "delete")
            Grid(viewModel.notesDataState.collectAsState(initial = emptyList()))
        }
    }

    @Composable
    fun Grid(items: State<List<NotesModel>>) {
        Box(
            modifier = Modifier
                .padding(top = 40.dp)
        ) {
            LazyVerticalGrid(columns = GridCells.Fixed(count = 2)) {
                items(items.value) {
                    NoteItem(it)
                }
                item {
                    NoteTextField()
                }
            }
        }
    }

    @Composable
    fun NoteItem(item: NotesModel) {
        Box(
            modifier = Modifier
                .padding(all = 10.dp)
                .height(200.dp)
                .width(IntrinsicSize.Max)
                .background(Color.Cyan)
                .padding(5.dp)
        ) {
            Text(
                text = item.note ?: "",
                style = TextStyle(color = Color.Yellow)
            )
        }
    }

    @Composable
    fun NoteTextField() {
        var text by remember { mutableStateOf("New Note") }
        Column(
            modifier = Modifier
                .padding(all = 10.dp)
                .height(200.dp)
                .width(IntrinsicSize.Max)
        ) {
            if (text.isNotEmpty()) Image(
                modifier = Modifier.align(Alignment.End).clickable { viewModel.addNote(text) },
                painter = painterResource(id = R.drawable.ic_check),
                contentDescription = "New Note"
            )
            TextField(
                modifier = Modifier
                    .background(Color.Cyan)
                    .fillMaxSize(),
                value = text,
                onValueChange = { text = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Cyan,
                    disabledContainerColor = Color.Cyan,
                    unfocusedContainerColor = Color.Cyan
                )
            )
        }
    }
}
