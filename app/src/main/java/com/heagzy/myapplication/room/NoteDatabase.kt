package com.heagzy.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 3)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao


    companion object {
        private var instance: NoteDatabase? = null
        fun getNoteDbInstance(context: Context): NoteDatabase? {
            if (instance == null) {
                synchronized(NoteDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NoteDatabase::class.java, "notes_database"
                    )
                        .fallbackToDestructiveMigration()
//                    .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }
    }


}