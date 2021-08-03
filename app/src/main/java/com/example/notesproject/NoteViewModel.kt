package com.example.notesproject

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    init {
        val dao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(dao)
        allNotes = repository.allNotes
    }

    fun deleteNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }                                     //this basically shows that we're doing input/ouput operations in this thread and we're launching it in viewModel scope or our background
                                                //this function will call repository and in that it'll call delete function of repository
                                                //toh jo activity hogi vo baat kregi viewModel se and viewModel baat krega repository se
    fun insertNote(note: Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

}

//ViewModel ko ni pata data kahan kahan se aane vaale hai , viewModel ka kaam hai data provide krna