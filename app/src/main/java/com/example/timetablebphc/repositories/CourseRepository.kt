package com.example.timetablebphc.repositories

import androidx.annotation.WorkerThread
import com.example.timetablebphc.courseDB.Course
import com.example.timetablebphc.dao.CourseDao
import javax.inject.Inject

class CourseRepository @Inject constructor(private val courseDao: CourseDao){

    @WorkerThread
    suspend fun insertCourse(course: Course) {
        courseDao.insertCourse(course)
    }

}