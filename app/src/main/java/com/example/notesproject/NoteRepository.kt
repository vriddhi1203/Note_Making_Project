package com.example.notesproject

import androidx.lifecycle.LiveData

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }

    suspend fun delete(note: Note){                 //suspend means that this function can be called via background thread or via
        noteDao.delete(note)                        //different suspend function
    }
}

//Repository ka kaam hai data ko leke aane ka