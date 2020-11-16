package com.heagzy.myapplication.repo

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.heagzy.myapplication.room.EmployeeDao
import com.heagzy.myapplication.room.EmployeeDatabase
import com.heagzy.myapplication.room.models.Note

class NoteRepository(application: Application) {

    private var employeeDao: EmployeeDao

    private var allNotes: LiveData<List<Note>>


    init {
        val database: EmployeeDatabase = EmployeeDatabase.getNoteDbInstance(
            application.applicationContext
        )!!
        employeeDao = database.noteDao()
        allNotes = employeeDao.getAllNotes()
    }

    fun insert(note: Note) {
        val insertNoteAsyncTask = InsertNoteAsyncTask(employeeDao).execute(note)
    }

    fun deleteAllNotes() {
        val deleteAllNotesAsyncTask = DeleteAllNotesAsyncTask(
            employeeDao
        ).execute()
    }


    fun update(note: Note) {
        val updateNoteAsyncTask = UpdateNoteAsyncTask(
            employeeDao
        ).apply {
            execute(note)
        }
    }


    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }

    private class InsertNoteAsyncTask(employeeDao: EmployeeDao) : AsyncTask<Note, Unit, Unit>() {
        val noteDao = employeeDao

        override fun doInBackground(vararg p0: Note?) {
            noteDao.insert(p0[0]!!)
        }
    }


    private class DeleteAllNotesAsyncTask(val employeeDao: EmployeeDao) :
        AsyncTask<Unit, Unit, Unit>() {

        override fun doInBackground(vararg p0: Unit?) {
            employeeDao.deleteAllNotes()
        }
    }


    private class UpdateNoteAsyncTask(val employeeDao: EmployeeDao) :
        AsyncTask<Note, Unit, Unit>() {

        override fun doInBackground(vararg params: Note?) {
            employeeDao.update(note = params[0])
        }


    }

}