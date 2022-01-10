package com.example.timetablebphc.repositories

import androidx.lifecycle.LiveData
import com.example.timetablebphc.courseDB.Course
import com.example.timetablebphc.dao.TimeTableDao
import javax.inject.Inject

class TimeTableRepository @Inject constructor(private val timeTableDao: TimeTableDao) {

    val allCourses: LiveData<List<Course>> = timeTableDao.getAllCourses()

    suspend fun deleteCourse(course: Course) {
        timeTableDao.delete(course)
    }
}