package com.example.timetablebphc.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.example.timetablebphc.courseDB.Course

@Dao
interface TimeTableDao {

    @Query("SELECT * FROM course_data_table")
    fun getAllCourses(): LiveData<List<Course>>

    @Delete
    suspend fun delete(course: Course) : Int
}