package com.heagzy.myapplication

import android.os.AsyncTask
import com.heagzy.myapplication.room.NoteDatabase

class PopulateDbAsyncTask(db: NoteDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val noteDao = db?.noteDao()

    override fun doInBackground(vararg p0: Unit?) {
//        noteDao?.insert(Note("Title 1", "description 1"))
//        noteDao?.insert(Note("Title 2", "description 2"))
//        noteDao?.insert(Note("Title 3", "description 3"))
    }
}