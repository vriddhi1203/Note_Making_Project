package com.example.notesproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this, this)
        recyclerView.adapter = adapter                        //first we're making recycler views then we're initialising view model

        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)  //attaching viewModel with the activity classes like onCreate onDelete
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)                                  //updated list is returning here
            }
        })

    }

    override fun onItemClicked(note: Note) {                    //our activity will only talk to viewModel and not anyone else
        viewModel.deleteNote(note)
        Toast.makeText(this,"'${note.text}' got DELETED", Toast.LENGTH_LONG).show()
    }

    fun submitData(view: View) {
        val noteText = input.text.toString()             //input vaale text se ye text leke aaega
        if(noteText.isNotEmpty()){
            viewModel.insertNote(Note(noteText))
            Toast.makeText(this, "'$noteText' got INSERTED", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Empty note cannot be inserted !", Toast.LENGTH_LONG).show()
        }
    }
}