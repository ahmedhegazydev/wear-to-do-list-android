package com.heagzy.myapplication.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

enum class STATUS {
    COMPLETED,
}


@Entity(tableName = "notes_table")
data class Note(
    var title: String,
    var description: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "note_status")
    var status: String = ""


}