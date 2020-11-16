package com.heagzy.myapplication.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.heagzy.myapplication.room.models.Note


@Dao
interface EmployeeDao {

    @Insert
    fun insert(note: Note)

    //    @Query("DELETE FROM notes_table")
    @Query("DELETE FROM employees_table")
    fun deleteAllNotes()

    //    @Query("SELECT * FROM notes_table ")
    @Query("SELECT * FROM employees_table ")
    fun getAllNotes(): LiveData<List<Note>>

    @Update
    fun update(note: Note?)

    @Delete
    fun delete(note: Note?)


    /**
     * Updating only price
     * By order id
     */
//    @Query("UPDATE orders SET notes_table=:price WHERE order_id = :id")
//    fun update(price: Float?, id: Int)

    /**
     * Updating only amount and price
     * By order id
     */
//    @Query("UPDATE orders SET order_amount = :amount, price = :price WHERE order_id =:id")
//    fun update(amount: Float?, price: Float?, id: Int)

    /**
     * Updating only title and description
     * By order id
     */
//    @Query("UPDATE orders SET order_desc = :description, order_title= :title WHERE order_id =:id")
//    fun update(description: String?, title: String?, id: Int)


}