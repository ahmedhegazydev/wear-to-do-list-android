package com.heagzy.myapplication.repo

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.heagzy.myapplication.room.Note
import com.heagzy.myapplication.room.NoteDao
import com.heagzy.myapplication.room.NoteDatabase

class NoteRepository(application: Application) {

    private var noteDao: NoteDao

    private var allNotes: LiveData<List<Note>>


    init {
        val database: NoteDatabase = NoteDatabase.getNoteDbInstance(
            application.applicationContext
        )!!
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            noteDao
        ).execute()
    }


    fun update(note: Note) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(
            noteDao
        ).apply {
            execute(note)
        }
    }


    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
        val noteDao = noteDao

        override fun doInBackground(vararg p0: Note?) {
            noteDao.insert(p0[0]!!)
        }
    }


    private class DeleteAllNotesAsyncTask(val noteDao: NoteDao) : AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            noteDao.deleteAllNotes()
        }
    }


    private class UpdateNoteAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {

        override fun doInBackground(vararg params: Note?) {
            noteDao.update(note = params[0])
        }


    }

}