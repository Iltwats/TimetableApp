package com.example.timetablebphc.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.timetablebphc.courseDB.Quiz

@Dao
interface HomeDao {

    @Query("SELECT * FROM quiz_data_table")
    fun getAllQuizzes(): LiveData<List<Quiz>>

    @Delete
    suspend fun delete(quiz: Quiz) : Int

}