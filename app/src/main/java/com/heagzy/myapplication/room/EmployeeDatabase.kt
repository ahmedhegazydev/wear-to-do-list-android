package com.heagzy.myapplication.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.heagzy.myapplication.room.models.Note

@Database(entities = [Note::class], version = 4)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun noteDao(): EmployeeDao


    companion object {
        private var instance: EmployeeDatabase? = null
        fun getNoteDbInstance(context: Context): EmployeeDatabase? {
            if (instance == null) {
                synchronized(EmployeeDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EmployeeDatabase::class.java, "notes_database"
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