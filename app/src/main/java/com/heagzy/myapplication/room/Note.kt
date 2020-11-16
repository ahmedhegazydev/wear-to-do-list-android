package com.heagzy.myapplication.room

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class STATUS {
    COMPLETED,
}


//@Entity(tableName = "notes_table")
//data class Note(
//    var title: String,
//    var description: String
//) {
//
//    @PrimaryKey(autoGenerate = true)
//    var id: Int = 0
//
//    @ColumnInfo(name = "note_status")
//    var status: String = ""
//
//}


@Entity(tableName = "employees_table")
data class Note(
    var empployeName: String,
    var employeeDept: String,
    var empployeAge: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

//    @ColumnInfo(name = "empployeName")
//    var name: String = ""
//
//    @ColumnInfo(name = "employeeDept")
//    var dept: String = ""
//
//    @ColumnInfo(name = "empployeAge")
//    var age: Int = 0


}