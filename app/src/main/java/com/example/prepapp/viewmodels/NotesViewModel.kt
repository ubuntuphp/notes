package com.example.prepapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.prepapp.api.ApiRepo
import com.example.prepapp.local.NotesDatabase
import com.example.prepapp.local.NotesModel
import com.example.prepapp.models.GithubModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val notesDao = NotesDatabase.getInstance(getApplication()).notesDao()
    val notesDataState = notesDao.getAllNotes()

    fun getLiveCount():SharedFlow<Int>{
        return ApiRepo.getLiveCounter()
    }

    fun addNote(note:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                notesDao.insert(NotesModel(note = note))
            }
        }
    }

    fun clearAllNotes(){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                notesDao.clear()
            }
        }
    }
}